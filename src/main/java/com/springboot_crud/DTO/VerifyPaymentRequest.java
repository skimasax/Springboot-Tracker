package com.springboot_crud.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VerifyPaymentRequest {
    @NotBlank( message = "reference is required")
    @JsonProperty("reference")
    private String reference;

    @NotNull(message = "user_id is required")
    @JsonProperty("user_id")
    private Long user;

    public VerifyPaymentRequest(String reference, Long user) {
        this.reference = reference;
        this.user = user;
    }

    public VerifyPaymentRequest() {
    }

    public @NotBlank(message = "reference is required") String getReference() {
        return reference;
    }

    public void setReference(@NotBlank(message = "reference is required") String reference) {
        this.reference = reference;
    }

    public @NotNull(message = "user_id is required") Long getUser() {
        return user;
    }

    public void setUser(@NotNull(message = "user_id is required") Long user) {
        this.user = user;
    }
}
