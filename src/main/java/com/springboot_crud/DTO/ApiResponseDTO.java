package com.springboot_crud.DTO;

import java.util.Map;


public class ApiResponseDTO {
    private boolean status;
    private String message;
    private Object data;



    public ApiResponseDTO(boolean status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ApiResponseDTO() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    public Map<String, String> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(Map<String, String> errorMessage) {
        this.errorMessage = errorMessage;
    }

    private Map <String,String> errorMessage;
}
