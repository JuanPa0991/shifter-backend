package com.shift.shift_planner_backend.turns;


import com.shift.shift_planner_backend.turns.model.TurnDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class TurnController {

    private final TurnService turnService;

    TurnController (TurnService turnService) {this.turnService=turnService;}

    @PostMapping("/turn")
    public ResponseEntity<Void> createTurn(@RequestBody TurnDTO createTurnDTO) {
        turnService.createTurn(createTurnDTO);
        return ResponseEntity.ok().build();
    }
}
