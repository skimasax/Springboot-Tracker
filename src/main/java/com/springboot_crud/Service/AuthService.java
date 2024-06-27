package com.springboot_crud.Service;

import com.springboot_crud.DTO.RegisterRequest;
import com.springboot_crud.Model.User;

public interface AuthService {
    User register(RegisterRequest registerRequest);
}
