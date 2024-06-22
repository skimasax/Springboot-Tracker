package com.springboot_crud.Controller;

import com.springboot_crud.DTO.ApiResponseDTO;
import com.springboot_crud.DTO.WalletTopupDTO;
import com.springboot_crud.Service.PaystackService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/wallet")
public class WalletController {

    @Autowired
    private final PaystackService paystackService;

    public WalletController(PaystackService paystackService) {
        this.paystackService = paystackService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO> initializePayment(@Valid @RequestBody WalletTopupDTO walletTopupDTO)
    {
        String data= this.paystackService.initiateTransaction(walletTopupDTO);
        ApiResponseDTO apiResponseDTO = new ApiResponseDTO(true,"payment, link fetched successfully", data);
        return ResponseEntity.ok(apiResponseDTO);
    }
}
