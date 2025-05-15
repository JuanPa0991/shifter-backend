package com.shift.shift_planner_backend.turns;

import com.shift.shift_planner_backend.turns.model.Turn;
import com.shift.shift_planner_backend.turns.model.TurnDTO;

public class TurnMapper {

    public static Turn toEntity (TurnDTO createTurnDTO) {

        return Turn.builder()
                .id(createTurnDTO.getId())
                .initDate(createTurnDTO.getInitDate())
                .endDate(createTurnDTO.getEndDate())
                .userName(createTurnDTO.getUserName())
                .initHour(createTurnDTO.getInitHour())
                .endHour(createTurnDTO.getEndHour())
                .userId(createTurnDTO.getUserId())
                .groupId(createTurnDTO.getGroupId())
                .build();
    }

    public static TurnDTO toDTO (Turn showTurn) {

        return TurnDTO.builder()
                .id(showTurn.getId())
                .initDate(showTurn.getInitDate())
                .endDate(showTurn.getEndDate())
                .userName(showTurn.getUserName())
                .initHour(showTurn.getInitHour())
                .endHour(showTurn.getEndHour())
                .userId(showTurn.getUserId())
                .groupId(showTurn.getGroupId())
                .build();
    }
}
