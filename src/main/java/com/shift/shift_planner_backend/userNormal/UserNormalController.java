package com.shift.shift_planner_backend.userNormal;

import com.shift.shift_planner_backend.userNormal.model.UserNormalDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class UserNormalController {

    private final UserNormalService userNormalService;

    UserNormalController (UserNormalService userNormalService) {this.userNormalService=userNormalService;}


    @PostMapping("/userNormal")
    public ResponseEntity<UserNormalDTO>createNormalUser (@RequestBody UserNormalDTO userNormalDTO) {
        return ResponseEntity.ok(userNormalService.createNormalUser(userNormalDTO));
    }

    @GetMapping("/userNormal")
    public ResponseEntity<List<UserNormalDTO>> findAllUsers() {
        List<UserNormalDTO> dtos = userNormalService.findAllDTOs();
        return ResponseEntity.ok(dtos);
    }
}