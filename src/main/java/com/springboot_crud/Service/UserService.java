package com.springboot_crud.Service;

import com.springboot_crud.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> allUsers();
    Optional<User> singleUser(Long id);
}
