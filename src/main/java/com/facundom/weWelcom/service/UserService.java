package com.facundom.weWelcom.service;

import com.facundom.weWelcom.exception.UserLoginException;
import com.facundom.weWelcom.exception.UserRegistrationException;
import com.facundom.weWelcom.entity.User;
import com.facundom.weWelcom.model.LoginRequest;
import com.facundom.weWelcom.model.UserDTO;
import com.facundom.weWelcom.model.UserResponseDTO;
import com.facundom.weWelcom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository repository;

    public UserResponseDTO register(UserDTO userDTO){

        if (repository.existsByUserEmail(userDTO.getUserEmail())){
            throw new UserRegistrationException("Email already taken.");
        }

        if (userDTO.getUserPassword().length() < 8){
            throw new IllegalArgumentException("Password should be at least 8 characters.");
        }
        //Hash the password
        String hashedPassword = BCrypt.hashpw(userDTO.getUserPassword(), BCrypt.gensalt());

        User user = User.builder()
                .userEmail(userDTO.getUserEmail())
                .userFirstName(userDTO.getUserFirstName())
                .userLastName(userDTO.getUserLastName())
                .userPassword(hashedPassword)
                .build();

        repository.save(user);

        Integer userId = user.getUserId();


        return UserResponseDTO.builder()
                .userId(userId)
                .userFirstName(userDTO.getUserFirstName())
                .userLastName(userDTO.getUserLastName())
                .userEmail(userDTO.getUserEmail())
                .build();
    }

    public UserResponseDTO login(LoginRequest request) {

        User existingUser = repository.findByUserEmail(request.getUserEmail());

        if (existingUser == null) {
            throw new UserLoginException("User not found");
        }
        if (!BCrypt.checkpw(request.getUserPassword(), existingUser.getUserPassword())) {
            throw new UserLoginException("Bad credentials");
        }

        Integer userId = existingUser.getUserId();
        String userName = existingUser.getUserFirstName();
        String userLastName = existingUser.getUserLastName();

        return UserResponseDTO.builder()
                .userEmail(request.getUserEmail())
                .userId(userId)
                .userFirstName(userName)
                .userLastName(userLastName)
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUserEmail(username);
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
}
