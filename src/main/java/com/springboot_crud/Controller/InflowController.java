package com.springboot_crud.Controller;

import com.springboot_crud.DTO.ApiResponseDTO;
import com.springboot_crud.DTO.InflowDTO;
import com.springboot_crud.Model.Inflow;
import com.springboot_crud.Model.User;
import com.springboot_crud.Service.impl.InflowServiceImpl;
import com.springboot_crud.Service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/inflow")
public class InflowController {
    @Autowired
    private final InflowServiceImpl inflowServiceImpl;
    @Autowired
    private final UserServiceImpl userServiceImpl;

    public InflowController(InflowServiceImpl inflowServiceImpl, UserServiceImpl userServiceImpl) {
        this.inflowServiceImpl = inflowServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping
    public ResponseEntity<ApiResponseDTO> allInflows()
    {
        List<Inflow> inflows = this.inflowServiceImpl.allInflows();
        ApiResponseDTO responseDTO = new ApiResponseDTO(true,"Inflows fetched successfully",inflows);
        return ResponseEntity.ok(responseDTO);

    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponseDTO> singleInflow(@PathVariable Long id)
    {
        Optional<Inflow> inflow = this.inflowServiceImpl.singleInflow(id);
            ApiResponseDTO apiResponseDTO = new ApiResponseDTO(true,"Inflow fetched successfully",inflow);
            return ResponseEntity.ok(apiResponseDTO);
    }


    @PostMapping
    public ResponseEntity<ApiResponseDTO> createInflow(@Valid @RequestBody InflowDTO inflowDTO , BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return getGenericResponseDTOResponseEntity(bindingResult);
        }

        //get the user
        Optional<User> userOptional = this.userServiceImpl.singleUser(inflowDTO.getUser());
        if(userOptional.isEmpty())
        {
            ApiResponseDTO responseDTO=new ApiResponseDTO(false, "User not found", new User());
            return ResponseEntity.status(404).body(responseDTO);
        }

        User user = userOptional.get();
        //save the inflow data
        Inflow inflow = new Inflow();
        inflow.setUser(user);
        inflow.setDescription(inflowDTO.getDescription());
        inflow.setQuantity(inflowDTO.getQuantity());
        inflow.setPrice(inflowDTO.getPrice());
        Double totalPrice=inflow.getQuantity() * inflow.getPrice();
        inflow.setTotalPrice(totalPrice);
        this.inflowServiceImpl.createInflow(inflow);
        ApiResponseDTO apiResponseDTO = new ApiResponseDTO(true,"Inflow created successfully",inflow);
        return ResponseEntity.ok(apiResponseDTO);


    }


    @DeleteMapping("/{id}")
    public  ResponseEntity <ApiResponseDTO> deleteInflow(@PathVariable Long id)
    {
        boolean isDeleted = this.inflowServiceImpl.deleteInflow(id);

        if (isDeleted) {
            ApiResponseDTO response = new ApiResponseDTO(true, "Inflow deleted successfully", new Inflow());
            return ResponseEntity.ok(response);
        } else {
            ApiResponseDTO response = new ApiResponseDTO(false, "Inflow not found", new Inflow());
            return ResponseEntity.status(404).body(response);
        }
    }



    public static ResponseEntity<ApiResponseDTO> getGenericResponseDTOResponseEntity(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : result.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        ApiResponseDTO errorResponse = new ApiResponseDTO();
        errorResponse.setStatus(false);
        errorResponse.setMessage("Validation error occurred");
        errorResponse.setErrorMessage(errors);;
        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }
}
