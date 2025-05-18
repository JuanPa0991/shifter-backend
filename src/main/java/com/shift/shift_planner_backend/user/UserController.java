package com.shift.shift_planner_backend.user;

import com.shift.shift_planner_backend.user.model.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class UserController {

    private final UserService userService;

    UserController (UserService userService) {this.userService=userService;}

    @PostMapping("/user")
    public ResponseEntity<UserDTO>createNormalUser (@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }


    @GetMapping("/user")
    public ResponseEntity<List<UserDTO>> findAllUsers() {
        List<UserDTO> dtos = userService.findAllDTOs();
        return ResponseEntity.ok(dtos);
    }
}
