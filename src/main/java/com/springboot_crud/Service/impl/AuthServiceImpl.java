package com.springboot_crud.Service.impl;

import com.springboot_crud.DTO.RegisterRequest;
import com.springboot_crud.Model.User;
import com.springboot_crud.Repository.UserRepository;
import com.springboot_crud.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public User register(RegisterRequest registerRequest)
    {
        Optional<User> optionalUser = userRepository.findByEmail(registerRequest.getEmail());
        if(optionalUser.isPresent())
        {
            throw new RuntimeException("Email already exist");
        }

        User user=new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setPhoneNumber("08135330301");
        //harsh the password before saving
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        return this.userRepository.save(user);
    }

}
