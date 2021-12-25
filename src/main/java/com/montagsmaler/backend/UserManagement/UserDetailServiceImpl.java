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

    public Optional<ArrayList<String>> updateUserDetails(UserUpdateDTO user, String userName) {
        UserEntity userEntityToUpdate = getUserEntityByName(userName);
        if (userEntityToUpdate != null){
            String newUserName= user.getBenutzername();
            boolean userNameAlreadyInUse = prepareUserNameInUseVar(user, newUserName);

            if(!userNameAlreadyInUse){
                if(newUserName != null){ userEntityToUpdate.setUserName(newUserName); }
                if(user.getPasswort() != null){ userEntityToUpdate.setPassword(passwordEncoder.encode(user.getPasswort())); }
                if(user.getAvatar() != null){ userEntityToUpdate.setAvatar(user.getAvatar()); }

                userRepository.save(userEntityToUpdate);
            } else {
                System.out.println("Failed to update");
                return Optional.of(getErrorFields(userNameAlreadyInUse));
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

    public ArrayList<String> getErrorFields(boolean userNameAlreadyInUse) {
        ArrayList<String> errorFields = new ArrayList<>();
        if (userNameAlreadyInUse){
            errorFields.add(USER_NAME_USED);
        }
        return errorFields;
    }

    public UUID getUserIDByName(String createrUserName) {
        return getUserEntityByName(createrUserName).getUserID();
    }
}
