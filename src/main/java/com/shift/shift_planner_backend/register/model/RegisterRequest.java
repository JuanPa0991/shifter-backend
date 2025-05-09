package com.shift.shift_planner_backend.register.model;

import com.shift.shift_planner_backend.user.model.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest extends UserDTO {
    
    public String password;
}
