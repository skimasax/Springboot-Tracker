package com.springboot_crud.Controller;

import com.springboot_crud.DTO.ApiResponseDTO;
import com.springboot_crud.DTO.VerifyPaymentRequest;
import com.springboot_crud.DTO.WalletTopupRequest;
import com.springboot_crud.Service.PaystackService;
import jakarta.validation.Valid;
import org.json.JSONObject;
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
    public ResponseEntity<ApiResponseDTO> initializePayment(@Valid @RequestBody WalletTopupRequest walletTopupRequest)
    {
        String data= this.paystackService.initiateTransaction(walletTopupRequest);
        ApiResponseDTO apiResponseDTO = new ApiResponseDTO(true,"payment, link fetched successfully", data);
        return ResponseEntity.ok(apiResponseDTO);
    }

    @PostMapping("/verify-wallet-payment")
    public ResponseEntity<ApiResponseDTO> verifyPayment(@Valid @RequestBody VerifyPaymentRequest verifyPaymentRequest)
    {
        String dataString= this.paystackService.verifyPaystackPayment(verifyPaymentRequest);
        JSONObject data = new JSONObject(dataString);
        boolean status = data.getBoolean("status");
        JSONObject email = data.optJSONObject("amount");
        ApiResponseDTO apiResponseDTO = new ApiResponseDTO(true,"payment link fetched successfully", data);
        return ResponseEntity.ok(apiResponseDTO);
    }


}
