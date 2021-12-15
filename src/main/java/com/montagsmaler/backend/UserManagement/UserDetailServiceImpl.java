package com.montagsmaler.backend.UserManagement;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static java.util.Collections.emptyList;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private static final String USER_NAME_USED = "userName:Der Nutzername ist bereits vergeben. Bitte wählen Sie einen anderen.";
    private static final String EMAIL_USED = "email:Die E-Mail Adresse wird bereits verwendet. Bitte wählen Sie eine andere.";
    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity userEntity = getUserEntityByName(userName);
        if (userEntity == null) {
            System.out.println("UserManagement not found");
            throw new UsernameNotFoundException(userName);
        }
        return new User(userEntity.getUserName(), userEntity.getPassword(), emptyList());
    }

    public Optional<UserDTO> getUserByName(String userName) {
        UserEntity result = getUserEntityByName(userName);
        if(result != null){
            UserDTO user = new UserDTO(result);
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    private UserEntity getUserEntityByName(String userName) {
        return userRepository.findExampleUserEntityByUserName(userName);
    }

    public void createUser(UserDTO user) {
        user.setPasswort(passwordEncoder.encode(user.getPasswort()));
        userRepository.save(new UserEntity(user));
    }

    public Optional<Object> getUserByEmail(String email) {
        UserEntity result = null;
        if(email != null){
            result = userRepository.findUserEntityByEmail(email.toLowerCase());
        }
        if(result != null){
            UserDTO user = new UserDTO(result);
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    public Optional<ArrayList<String>> updateUserDetails(UserUpdateDTO user, String userName) {
        UserEntity userEntityToUpdate = getUserEntityByName(userName);
        if (userEntityToUpdate != null){
            String newUserName= user.getBenutzername();
            String newEmail = user.getEmail();
            boolean userNameAlreadyInUse = prepareUserNameInUseVar(user, newUserName);
            boolean emailAlreadyInUse = prepareEmailInUseVar(user, newEmail);

            if(!userNameAlreadyInUse && !emailAlreadyInUse){
                if(newUserName != null){ userEntityToUpdate.setUserName(newUserName); }
                if(newEmail != null){ userEntityToUpdate.setEmail(newEmail.toLowerCase()); }
                if(user.getAlter() != null){ userEntityToUpdate.setAge(user.getAlter()); }
                if(user.getPasswort() != null){ userEntityToUpdate.setPassword(passwordEncoder.encode(user.getPasswort())); }

                userRepository.save(userEntityToUpdate);
            } else {
                System.out.println("Failed to update");
                return Optional.of(getErrorFields(userNameAlreadyInUse, emailAlreadyInUse));
            }
        }
        return Optional.empty();
    }

    private boolean prepareUserNameInUseVar(UserUpdateDTO user, String newUserName) {
        boolean userNameAlreadyInUse = false;
        if(user.getBenutzername() != null){
            userNameAlreadyInUse = getUserByName(newUserName).isPresent();
        }
        return userNameAlreadyInUse;
    }

    private boolean prepareEmailInUseVar(UserUpdateDTO user, String newEmail) {
        boolean emailAlreadyInUse = false;
        if(user.getEmail() != null){
            emailAlreadyInUse = getUserByEmail(newEmail).isPresent();
        }
        return emailAlreadyInUse;
    }

    public ArrayList<String> getErrorFields(boolean userNameAlreadyInUse, boolean emailAlreadyInUse) {
        ArrayList<String> errorFields = new ArrayList<>();
        if (userNameAlreadyInUse){
            errorFields.add(USER_NAME_USED);
        }
        if(emailAlreadyInUse){
            errorFields.add(EMAIL_USED);
        }
        return errorFields;
    }

    public UUID getUserIDByName(String createrUserName) {
        return getUserEntityByName(createrUserName).getUserID();
    }
}
