package com.shift.shift_planner_backend.turns.model;


import lombok.*;

import java.time.LocalDate;

/**
 * DTO que representa un turno en la capa de transferencia de datos.
 * Utilizado para enviar o recibir información de turnos desde el cliente.
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class TurnDTO {

    private Long id;
    private LocalDate initDate;
    private LocalDate endDate;
    private String userName;
    private String initHour;
    private String endHour;
    private Long userId;
    private Long groupId;
}
