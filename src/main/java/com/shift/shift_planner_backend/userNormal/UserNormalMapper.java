package com.shift.shift_planner_backend.userNormal;

import com.shift.shift_planner_backend.userNormal.model.UserNormal;
import com.shift.shift_planner_backend.userNormal.model.UserNormalDTO;

import java.util.ArrayList;
import java.util.List;

public class UserNormalMapper {

    public static UserNormal toEntity (UserNormalDTO userNormalDTO) {

        return UserNormal.builder()
                .name(userNormalDTO.getName())
                .lastName(userNormalDTO.getLastName())
                .companyName(userNormalDTO.getCompanyName())
                .dni(userNormalDTO.getDni())
                .id(userNormalDTO.getId())
                .email(userNormalDTO.getEmail())
                .build();
    }

    public static UserNormalDTO toDTO(UserNormal userNormal) {
        return UserNormalDTO.builder()
                .id(userNormal.getId())
                .name(userNormal.getName())
                .lastName(userNormal.getLastName())
                .companyName(userNormal.getCompanyName())
                .dni(userNormal.getDni())
                .email(userNormal.getEmail())
                .build();
    }

    public static List<UserNormalDTO> toDTOs(List<UserNormal> users) {
        List<UserNormalDTO> dtos = new ArrayList<>(users.size());
        for (UserNormal user : users) {
            dtos.add(toDTO(user));
        }
        return dtos;
    }
}
