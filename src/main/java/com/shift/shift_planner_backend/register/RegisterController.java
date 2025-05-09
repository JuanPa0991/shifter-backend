package com.shift.shift_planner_backend.register;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shift.shift_planner_backend.register.model.RegisterRequest;
import com.shift.shift_planner_backend.register.model.RegisterResponse;



import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class RegisterController {

    private final RegisterService registerService;

    RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(@RequestBody RegisterRequest registerRequest) {
          
         return ResponseEntity.ok(registerService.registerUser(registerRequest));
    }  
}
