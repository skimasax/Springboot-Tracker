package com.springboot_crud.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class WalletTopupDTO {
    @NotNull(message = "user_id is required")
    @JsonProperty("user_id")
    private Long user;

    @NotNull(message = "amount is required")
    @JsonProperty("amount")
    private Double amount;

    public WalletTopupDTO(Long user, Double amount) {
        this.user = user;
        this.amount = amount;
    }

    public WalletTopupDTO() {
    }

    public @NotNull(message = "user_id is required") Long getUser() {
        return user;
    }

    public void setUser(@NotNull(message = "user_id is required") Long user) {
        this.user = user;
    }

    public @NotNull(message = "email is required") Double getAmount() {
        return amount;
    }

    public void setAmount(@NotNull(message = "email is required") Double amount) {
        this.amount = amount;
    }
}
