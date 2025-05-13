package com.shift.shift_planner_backend.turns;

import com.shift.shift_planner_backend.turns.model.Turn;
import com.shift.shift_planner_backend.turns.model.TurnDTO;

public class TurnMapper {

    public static Turn toEntity (TurnDTO createTurnDTO) {

        return Turn.builder()
                .initDate(createTurnDTO.getInitDate())
                .endDate(createTurnDTO.getEndDate())
                .userName(createTurnDTO.getUserName())
                .initHour(createTurnDTO.getInitHour())
                .endHour(createTurnDTO.getEndHour())
                .build();
    }

    public static TurnDTO toDTO (Turn showTurn) {

        return TurnDTO.builder()
                .initDate(showTurn.getInitDate())
                .endDate(showTurn.getEndDate())
                .userName(showTurn.getUserName())
                .initHour(showTurn.getInitHour())
                .endHour(showTurn.getEndHour())
                .build();
    }
}
