package com.springboot_crud.Controller;

import com.springboot_crud.DTO.RegisterDTO;
import com.springboot_crud.DTO.ApiResponseDTO;
import com.springboot_crud.Model.User;
import com.springboot_crud.Model.Wallet;
import com.springboot_crud.Service.AuthService;
import com.springboot_crud.Service.WalletService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDTO> register(@Valid @RequestBody RegisterDTO registerDTO, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return getGenericResponseDTOResponseEntity(bindingResult);
        }
        //create the user
        User newUser= this.authService.register(registerDTO);
        System.out.println(newUser);
        //create an object of the wallet
        Wallet wallet=new Wallet();
        wallet.setUser(newUser);
        wallet.setWalletNumber(1234580);
        wallet.setWalletCredit(BigDecimal.valueOf(0));
        wallet.setWalletDebit(BigDecimal.valueOf(0.0));
        BigDecimal walletCredit = wallet.getWalletCredit();
        BigDecimal walletDebit = wallet.getWalletDebit();
        BigDecimal total = walletCredit.subtract(walletDebit);
        wallet.setTotal(total);
        Wallet newWallet = this.walletService.createWallet(wallet);
        newUser.setWallet(newWallet); // Set the newly created wallet to the user
         ApiResponseDTO response = new ApiResponseDTO(true,"register successfully",newUser);
         return ResponseEntity.ok(response);

    }

//    @PostMapping("/login")
//    public ResponseEntity <ResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO, BindingResult bindingResult){
//        if(bindingResult.hasErrors())
//        {
//            return getGenericResponseDTOResponseEntity(bindingResult);
//        }
//
//
//
//    }

    public static ResponseEntity<ApiResponseDTO> getGenericResponseDTOResponseEntity(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : result.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        ApiResponseDTO errorResponse = new ApiResponseDTO();
        errorResponse.setStatus(false);
        errorResponse.setMessage("Validation error occurred");
        errorResponse.setErrorMessage(errors);;
        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }
}
