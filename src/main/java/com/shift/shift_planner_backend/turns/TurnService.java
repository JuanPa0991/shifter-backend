package com.shift.shift_planner_backend.turns;


import com.shift.shift_planner_backend.turns.model.Turn;
import com.shift.shift_planner_backend.turns.model.TurnDTO;
import org.springframework.stereotype.Service;

@Service
public class TurnService {

    private final TurnRepository turnRepository;

    public TurnService(TurnRepository turnRepository) {
        this.turnRepository = turnRepository;
    }

    public TurnDTO createTurn (TurnDTO createTurnDTO) {
        turnRepository.findByInitDateAndEndDateAndUserName(
                createTurnDTO.getInitDate(),
                createTurnDTO.getEndDate(),
                createTurnDTO.getUserName()
        ).ifPresent(t -> {
            throw new RuntimeException("Ya existe un turno con esos datos.");
        });


        Turn turnMapped = TurnMapper.toEntity((createTurnDTO));
        Turn saved = turnRepository.save(turnMapped);

        return TurnMapper.toDTO(saved);


    }
}
