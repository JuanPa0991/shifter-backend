package com.shift.shift_planner_backend.user;

import com.shift.shift_planner_backend.register.model.RegisterRequest;
import com.shift.shift_planner_backend.user.model.User;

public class UserMapper {
    
    public static User toEntity (RegisterRequest registerRequest) {

        return User.builder() 
        .name(registerRequest.getName())
        .lastName(registerRequest.getLastName())
        .companyName(registerRequest.getCompanyName())
        .dni(registerRequest.getDni())
        .id(registerRequest.getId())
        .email(registerRequest.getEmail())
        .password(registerRequest.getPassword())
        .build();
    }
}
