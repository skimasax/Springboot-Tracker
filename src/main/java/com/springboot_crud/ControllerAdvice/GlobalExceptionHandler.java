package com.springboot_crud.ControllerAdvice;

import com.springboot_crud.DTO.ApiResponseDTO;
import com.springboot_crud.Exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody

    public ResponseEntity<ApiResponseDTO> handleCustomException(CustomException ex) {
        ApiResponseDTO response = new ApiResponseDTO(false, ex.getMessage(), new ApiResponseDTO());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<ApiResponseDTO> handleException(Exception ex) {
        ApiResponseDTO response = new ApiResponseDTO(false, "An unexpected error occurred", new ApiResponseDTO());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
