package com.springboot_crud.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor

public class RegisterRequest {
    @NotBlank(message = "First name is required")
    @JsonProperty("first_name")
    private String firstName;

    @NotBlank(message = "Lastname is requried")
    @JsonProperty("last_name")
    private String lastName;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password requires minimum of 8")
    @JsonProperty("password")
    private String password;

    @NotBlank(message = "Email is required")
    @JsonProperty("email")
    private String email;

    public @NotBlank(message = "First name is required") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "First name is required") String firstName) {
        this.firstName = firstName;
    }

    public @NotBlank(message = "Lastname is requried") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = "Lastname is requried") String lastName) {
        this.lastName = lastName;
    }

    public @NotBlank(message = "Password is required") @Size(min = 8, message = "Password requires minimum of 8") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is required") @Size(min = 8, message = "Password requires minimum of 8") String password) {
        this.password = password;
    }

    public @NotBlank(message = "Email is required") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is required") String email) {
        this.email = email;
    }
}
