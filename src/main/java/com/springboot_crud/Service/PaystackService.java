package com.springboot_crud.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot_crud.DTO.WalletTopupDTO;
import com.springboot_crud.Exception.CustomException;
import com.springboot_crud.Model.User;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PaystackService {

    @Autowired
    private final UserService userService;


    @Value("${paystack.secret.key}")
    private String secretKey;

    private static final String BASE_URL = "https://api.paystack.co";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public PaystackService(UserService userService) {
        this.userService = userService;
    }

    public String verifyPayment(String reference) throws CustomException {
        String url = BASE_URL + "/transaction/verify/" + reference;

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            request.setHeader("Authorization", "Bearer " + secretKey);
            request.setHeader("Content-type", "application/json");

            try (CloseableHttpResponse response = client.execute(request)) {
                if (response.getStatusLine().getStatusCode() != HttpStatus.OK.value()) {
                    throw new CustomException("Error verifying payment: " + response.getStatusLine().getReasonPhrase());
                }
                return EntityUtils.toString(response.getEntity());
            }
        } catch (IOException e) {
            throw new CustomException("Error verifying payment: " + e.getMessage(), e);
        }
    }

    public String initiateTransaction(WalletTopupDTO walletTopupDTO) throws CustomException {
        String url = BASE_URL + "/transaction/initialize";

        Optional<User> userOptional=this.userService.singleUser(walletTopupDTO.getUser());
        if(userOptional.isEmpty()){
            throw new CustomException("User does not exist");
        }

        User user = userOptional.get();

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("email", user.getEmail());
        requestBody.put("amount", walletTopupDTO.getAmount());

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(url);
            request.setHeader("Authorization", "Bearer " + secretKey);
            request.setHeader("Content-type", "application/json");

            String json = objectMapper.writeValueAsString(requestBody);
            request.setEntity(new StringEntity(json));

            try (CloseableHttpResponse response = client.execute(request)) {
                if (response.getStatusLine().getStatusCode() != HttpStatus.OK.value()) {
                    throw new CustomException("Error initiating transaction: " + response.getStatusLine().getReasonPhrase());
                }
                return EntityUtils.toString(response.getEntity());
            }
        } catch (IOException e) {
            throw new CustomException("Error initiating transaction: " + e.getMessage(), e);
        }
    }
}
