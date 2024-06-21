package com.springboot_crud.Controller;

import com.springboot_crud.DTO.ApiResponseDTO;
import com.springboot_crud.DTO.InflowDTO;
import com.springboot_crud.ENUM.PaymentStatus;
import com.springboot_crud.Model.Inflow;
import com.springboot_crud.Model.User;
import com.springboot_crud.Service.InflowsService;
import com.springboot_crud.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.springboot_crud.Controller.AuthController.getGenericResponseDTOResponseEntity;

@RestController
@RequestMapping("api/v1/inflow")
public class InflowController {
    @Autowired
    private final InflowsService inflowsService;
    @Autowired
    private final UserService userService;

    public InflowController(InflowsService inflowsService, UserService userService) {
        this.inflowsService = inflowsService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponseDTO> allInflows()
    {
        List<Inflow> inflows = this.inflowsService.allInflows();
        ApiResponseDTO responseDTO = new ApiResponseDTO(true,"Inflows fetched successfully",inflows);
        return ResponseEntity.ok(responseDTO);

    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO> createInflow(@Valid @RequestBody InflowDTO inflowDTO , BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return getGenericResponseDTOResponseEntity(bindingResult);
        }

        //get the user
        Optional<User> userOptional = this.userService.singleUser(inflowDTO.getUser());
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
        this.inflowsService.createInflow(inflow);
        ApiResponseDTO apiResponseDTO = new ApiResponseDTO(true,"Inflow created successfully",inflow);
        return ResponseEntity.ok(apiResponseDTO);


    }


    @DeleteMapping("/{id}")
    public  ResponseEntity <ApiResponseDTO> deleteInflow(@PathVariable Long id)
    {
        boolean isDeleted = this.inflowsService.deleteInflow(id);

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
