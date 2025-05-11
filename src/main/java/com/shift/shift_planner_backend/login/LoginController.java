package com.shift.shift_planner_backend.login;

// Imports
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Importamos el LoginRequest y el LoginResponse
import com.shift.shift_planner_backend.login.model.LoginRequest;
import com.shift.shift_planner_backend.login.model.LoginResponse;

@RestController // Marca la clase como controlador REST
@RequestMapping("/api") // Define la ruta base para todos los endpoints
public class LoginController {

    private final LoginService loginService; // Inyección del servicio de login

    //Constructor para inyección de dependencias
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login") // Maneja peiticiones POST a /api/login
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(loginService.login(loginRequest)); // Devuelve respuesta HTTP 200 con el resultado
    }
    
}
