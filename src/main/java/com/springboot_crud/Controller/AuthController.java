package com.springboot_crud.Controller;

import com.springboot_crud.DTO.RegisterRequest;
import com.springboot_crud.DTO.ApiResponseDTO;
import com.springboot_crud.Model.User;
import com.springboot_crud.Model.Wallet;
import com.springboot_crud.Service.impl.AuthServiceImpl;
import com.springboot_crud.Service.impl.WalletServiceImpl;
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
    public final AuthServiceImpl authServiceImpl;
    public final WalletServiceImpl walletServiceImpl;

    public AuthController(AuthServiceImpl authServiceImpl, WalletServiceImpl walletServiceImpl)
    {
        this.authServiceImpl=authServiceImpl;
        this.walletServiceImpl=walletServiceImpl;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDTO> register(@Valid @RequestBody RegisterRequest registerRequest, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return getGenericResponseDTOResponseEntity(bindingResult);
        }
        //create the user
        User newUser= this.authServiceImpl.register(registerRequest);
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
        Wallet newWallet = this.walletServiceImpl.createWallet(wallet);
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
