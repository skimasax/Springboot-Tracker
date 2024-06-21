package com.springboot_crud.Service;

import com.springboot_crud.DTO.RegisterDTO;
import com.springboot_crud.Model.User;
import com.springboot_crud.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

        @Autowired
            public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder)
            {
                this.userRepository=userRepository;
                this.passwordEncoder=passwordEncoder;
            }

            public User register(RegisterDTO registerDTO)
            {
                Optional<User> optionalUser = userRepository.findByEmail(registerDTO.getEmail());
                if(optionalUser.isPresent())
                {
                    throw new RuntimeException("Email already exist");
                }

                User user=new User();
                user.setFirstName(registerDTO.getFirstName());
                user.setLastName(registerDTO.getLastName());
                //harsh the password before saving
                user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
                user.setEmail(registerDTO.getEmail());
                return this.userRepository.save(user);
            }



}
