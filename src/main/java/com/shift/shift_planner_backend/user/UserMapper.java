package com.shift.shift_planner_backend.user;

import com.shift.shift_planner_backend.register.model.RegisterRequest;
import com.shift.shift_planner_backend.user.model.User;
import com.shift.shift_planner_backend.user.model.UserDTO;


import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    
    public static User toEntity (RegisterRequest registerRequest) {

        return User.builder() 
                .name(registerRequest.getName())
                .lastName(registerRequest.getLastName())
                .companyName(registerRequest.getCompanyName())
                .dni(registerRequest.getDni())
                .id(registerRequest.getId())
                .email(registerRequest.getEmail())
                .isAdmin(true)
                .password(registerRequest.getPassword())
                .build();
    }

    public static User toEntity (UserDTO userDTO) {

        return User.builder()
                .name(userDTO.getName())
                .lastName(userDTO.getLastName())
                .companyName(userDTO.getCompanyName())
                .dni(userDTO.getDni())
                .id(userDTO.getId())
                .email(userDTO.getEmail())
                .isAdmin(userDTO.getIsAdmin())
                .build();
    }


    public static UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .companyName(user.getCompanyName())
                .dni(user.getDni())
                .email(user.getEmail())
                .isAdmin(user.getIsAdmin())
                .build();
    }

    public static List<UserDTO> toDTOs(List<User> users) {
        List<UserDTO> dtos = new ArrayList<>(users.size());
        for (User user : users) {
            dtos.add(toDTO(user));
        }
        return dtos;
    }
}

