package com.shift.shift_planner_backend.turns.model;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TurnFilterDTO {
    private LocalDate initDate;
    private LocalDate endDate;
    private String userName;
    private Long userId;
    private String initHour;
    private String endHour;
    private Long groupId;
}

