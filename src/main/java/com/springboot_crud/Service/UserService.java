package com.springboot_crud.Service;

import com.springboot_crud.Exception.CustomException;
import com.springboot_crud.Model.User;
import com.springboot_crud.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers()
    {
        return userRepository.findAll();
    }

    public Optional<User> singleUser(Long id)
    {
        Optional<User> optionalUser=userRepository.findById(id);
        if(optionalUser.isEmpty())
        {
            throw new CustomException("User not found");
        }else{
            return optionalUser;
        }

    }
}
