package com.springboot_crud.DTO;

public class UserResponseDTO {
    private String fullName;
    private String lastName;
    private String email;

    public UserResponseDTO(String fullName, String lastName, String email) {
        this.fullName = fullName;
        this.lastName = lastName;
        this.email = email;
    }

    public UserResponseDTO() {
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
