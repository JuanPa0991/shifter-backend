package com.shift.shift_planner_backend.login.model;

// Import del User
import com.shift.shift_planner_backend.user.model.UserDTO;

// Imports
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor // Genera constructor con todos los argumentos
@Builder // Permite crear objetos usando el patrón Builder
@Data // Genera automáticamente getters, setters, toString, equals y hashCode
@NoArgsConstructor // Genera constructor sin argumentos

public class LoginRequest {

    private String email; // Variable para guardar email del usuario
    private String password; // Variable para guardar la contraseña
    
}
