package com.montagsmaler.backend.userManagement;

import com.montagsmaler.backend.userManagement.dto.AuthenticationRequest;
import com.montagsmaler.backend.userManagement.dto.AuthenticationResponse;
import com.montagsmaler.backend.helper.ValidationError;
import com.montagsmaler.backend.security.JwtService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(UserController.ROOT_MAPPING)
@EnableAutoConfiguration
public class UserController {
    static final String ROOT_MAPPING = "/backend/user";
    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private UserDetailServiceImpl userDetailService;

    @Resource
    private JwtService jwtService;

    @PostMapping(value="/signup")
    public ResponseEntity userSignUp(@RequestBody @Valid UserDTO user) {
        String userName = user.getBenutzername();
        boolean userNameAlreadyInUse = userDetailService.getUserByName(userName).isPresent();

        if(!userNameAlreadyInUse){
            userDetailService.createUser(user);
            return status(CREATED).build();
        }
        ArrayList<String> errorFields = userDetailService.getErrorFields(userNameAlreadyInUse);
        return status(BAD_REQUEST).body(new ValidationError(errorFields));
    }


    @PostMapping(value="/update")
    public ResponseEntity updateUser(@RequestBody UserUpdateDTO user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Optional<ArrayList<String>> errorFields = userDetailService.updateUserDetails(user, userName);
        if(!errorFields.isPresent()){
            String newUserName = user.getBenutzername() == null ? userName : user.getBenutzername();
            return ResponseEntity.ok(prepareAuthenticationResponse(newUserName));
        }
        return status(BAD_REQUEST).body(new ValidationError(errorFields.get()));
    }

    @GetMapping(value="/details")
    public UserResponseDTO getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Optional<UserResponseDTO> user = userDetailService.getUserByName(userName);
        if(user.isPresent()){
            return user.get();
        }
        else
        {
            throw new NoSuchElementException("UserDTO with name: " + userName + "not found");
        }
    }

    @PostMapping(value="/authenticate")
    public ResponseEntity authenticate(@RequestBody @Valid AuthenticationRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid username or password", e);
        }

        return ResponseEntity.ok(prepareAuthenticationResponse(authRequest.getUserName()));
    }

    private AuthenticationResponse prepareAuthenticationResponse(String userName) {
        final UserDetails userDetails = userDetailService.loadUserByUsername(userName);
        final String jwt = jwtService.generateToken(userDetails);
        return new AuthenticationResponse(jwt);
    }
}

