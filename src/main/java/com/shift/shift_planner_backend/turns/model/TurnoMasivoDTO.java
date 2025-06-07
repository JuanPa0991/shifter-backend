package com.shift.shift_planner_backend.turns.model;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


/**
 * DTO para la creación masiva de turnos.
 * Permite especificar un rango de fechas, horario común y usuarios o grupo destino.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TurnoMasivoDTO {
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<Long> idsUsuarios; // Lista de IDs de usuarios
    private Long idGrupo;
}
