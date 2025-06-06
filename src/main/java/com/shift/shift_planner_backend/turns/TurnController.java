package com.shift.shift_planner_backend.turns;


import com.shift.shift_planner_backend.turns.model.TurnFilterDTO;
import com.shift.shift_planner_backend.turns.model.TurnDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// import com.shift.shift_planner_backend.turns.model.TurnFilterDTO;
import com.shift.shift_planner_backend.turns.model.TurnDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("api")
public class TurnController {

    private final TurnService turnService;

    TurnController (TurnService turnService) {this.turnService=turnService;}

    @PostMapping("/turn")
    public ResponseEntity<TurnDTO> createTurn(@RequestBody TurnDTO createTurnDTO) {

        return ResponseEntity.ok(turnService.createTurn(createTurnDTO));
    }

    @PostMapping("/turns/filter")
    public ResponseEntity<List<TurnDTO>> filterTurns(@RequestBody TurnFilterDTO filter) {
        List<TurnDTO> result = turnService.filterTurns(filter);
        return ResponseEntity.ok(result);
    }

    // @PostMapping("/turns/filter")
    // public ResponseEntity<List<TurnDTO>> filterTurns(@RequestBody TurnFilterDTO filter) {
    //     List<TurnDTO> result = turnService.filterTurns(filter);
    //     return ResponseEntity.ok(result);
    // }

    @GetMapping("/turns/all")
    public List<TurnDTO> getAllTurns() {
        return turnService.findAllDTOs();
    }

    @DeleteMapping("/turns/{id}")
    public ResponseEntity<Void> deleteTurn(@PathVariable Long id) {
        turnService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


