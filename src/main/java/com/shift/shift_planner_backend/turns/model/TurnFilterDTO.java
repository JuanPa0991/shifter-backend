package com.shift.shift_planner_backend.turns.model;

import lombok.*;
import java.time.LocalDate;

/**
 * DTO utilizado para filtrar turnos en las consultas.
 * Contiene campos opcionales que pueden usarse como criterios de búsqueda.
 */
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

