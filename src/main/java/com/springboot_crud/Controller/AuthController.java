package com.springboot_crud.Controller;

import com.springboot_crud.DTO.RegisterDTO;
import com.springboot_crud.DTO.ResponseDTO;
import com.springboot_crud.Entity.User;
import com.springboot_crud.Entity.Wallet;
import com.springboot_crud.Service.AuthService;
import com.springboot_crud.Service.WalletService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    public final AuthService authService;
    public final WalletService walletService;

    public AuthController(AuthService authService, WalletService walletService)
    {
        this.authService=authService;
        this.walletService=walletService;
    }

    @PostMapping("/user")
    public ResponseEntity<ResponseDTO> register(@Valid @RequestBody RegisterDTO registerDTO, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return getGenericResponseDTOResponseEntity(bindingResult);
        }
        //create the user
        System.out.println(registerDTO);
        User newUser= this.authService.register(registerDTO);
        //create an object of the wallet
        Wallet wallet=new Wallet();
        wallet.setUser(newUser);
        wallet.setWalletNumber(12345890);
        wallet.setWalletCredit(BigDecimal.valueOf(0));
        wallet.setWalletDebit(BigDecimal.valueOf(0.0));// Initialize wallet balance
        Wallet newWallet = this.walletService.createWallet(wallet);
        newUser.setWallet(newWallet); // Set the newly created wallet to the user
         ResponseDTO response = new ResponseDTO<>(true,"register successfully",newUser);
         return ResponseEntity.ok(response);

    }

    public static ResponseEntity<ResponseDTO> getGenericResponseDTOResponseEntity(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : result.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        ResponseDTO errorResponse = new ResponseDTO();
        errorResponse.setStatus(false);
        errorResponse.setMessage("Validation error occurred");
        errorResponse.setErrorMessage(errors);;
        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }
}
