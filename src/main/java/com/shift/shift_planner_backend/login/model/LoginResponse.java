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

public class LoginResponse {
    private Long userId; // Id del usuario que se devolverá tras un login exitoso
    private String name; // Nombre del usuario que se devolverá tras un login exitoso
    private String token; // Token JWT que se usará para autenticación
    private Boolean isAdmin; // Indica si el usuario es administrador
}
