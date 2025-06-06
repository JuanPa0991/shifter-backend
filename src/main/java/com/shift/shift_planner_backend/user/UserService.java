package com.shift.shift_planner_backend.user;

import com.shift.shift_planner_backend.user.model.User;
import com.shift.shift_planner_backend.user.model.UserDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findAllDTOs() {
        Iterable<User> iterable = userRepository.findAll();
        List<UserDTO> users = new ArrayList<>();
        for (User user : iterable) {
            users.add(UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .dni(user.getDni())
                .email(user.getEmail())
                .companyName(user.getCompanyName())
                .build());
        }
        return users;
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
