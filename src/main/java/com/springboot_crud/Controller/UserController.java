package com.springboot_crud.Controller;

import com.springboot_crud.DTO.ResponseDTO;
import com.springboot_crud.DTO.UserResponseDTO;
import com.springboot_crud.Entity.User;
import com.springboot_crud.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> allUsers(){
            List<User> users = userService.allUsers();
            ResponseDTO responseDTO=new ResponseDTO(true,"Users fetched successfully",users);
            return ResponseEntity.ok(responseDTO);
    }

}
