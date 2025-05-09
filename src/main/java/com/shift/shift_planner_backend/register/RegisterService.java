package com.shift.shift_planner_backend.register;
import org.springframework.stereotype.Service;

import com.shift.shift_planner_backend.register.model.RegisterRequest;
import com.shift.shift_planner_backend.register.model.RegisterResponse;
import com.shift.shift_planner_backend.user.UserMapper;
import com.shift.shift_planner_backend.user.UserRepository;
import com.shift.shift_planner_backend.user.model.User;


@Service
public class RegisterService {



    private final UserRepository userRepository;

    public RegisterService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public RegisterResponse registerUser(RegisterRequest registerRequest) {
        userRepository.findByEmail(registerRequest.getEmail())
                      .ifPresent(u -> {throw new RuntimeException("El email ya existe");});
        User userMapped = UserMapper.toEntity(registerRequest);
        User userSaved = userRepository.save(userMapped);

        return RegisterResponse.builder().name(userSaved.getName()).build();
    }
}
