package com.shift.shift_planner_backend.login;

import org.springframework.stereotype.Service;

import com.shift.shift_planner_backend.login.model.LoginRequest;
import com.shift.shift_planner_backend.login.model.LoginResponse;
import com.shift.shift_planner_backend.user.UserRepository;
import com.shift.shift_planner_backend.user.model.User;

@Service // Marca la clase como un servicio de Spring
public class LoginService {
    
    private final UserRepository userRepository; // Inyección del repositorio de usuarios

    // Constructor para inyección de dependencias
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        // Busca el usuario por email y lanzará una excepción si no existe
        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Aquí se debería de verificar la contraseña e implementar la verificación de contraseña
        // Construye y devuelve la respuesta
        return LoginResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .token("token-temportal")
                .isAdmin(user.getIsAdmin())
                .build();
    }

}
