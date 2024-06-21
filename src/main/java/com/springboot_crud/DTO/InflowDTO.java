package com.springboot_crud.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.EnumNaming;
import com.springboot_crud.Model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class InflowDTO {
    @NotNull(message = "user_id is required")
    @JsonProperty("user_id")
    private Long user;

    @NotBlank(message = "description is required")
    @JsonProperty("description")
    private String Description;

    @NotNull(message = "Quantity is required")
    @JsonProperty("quantity")
    private Integer quantity;

    @NotNull(message = "Price is required")
    @JsonProperty("price")
    private Double Price;


    public InflowDTO(Long user, String description, Integer quantity, Double price) {
        this.user = user;
        Description = description;
        this.quantity = quantity;
        Price = price;
    }

    public InflowDTO() {
    }

    public @NotNull(message = "user_id is required") Long getUser() {
        return user;
    }

    public void setUser(@NotNull(message = "user_id is required") Long user) {
        this.user = user;
    }

    public @NotBlank(message = "description is required") String getDescription() {
        return Description;
    }

    public void setDescription(@NotBlank(message = "description is required") String description) {
        Description = description;
    }

    public @NotNull(message = "Quantity is required") Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(@NotNull(message = "Quantity is required") Integer quantity) {
        this.quantity = quantity;
    }

    public @NotNull(message = "Price is required") Double getPrice() {
        return Price;
    }

    public void setPrice(@NotNull(message = "Price is required") Double price) {
        Price = price;
    }



}

