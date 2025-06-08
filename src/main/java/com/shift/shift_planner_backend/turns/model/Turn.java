package com.shift.shift_planner_backend.turns.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Entidad que representa un turno asignado a un usuario.
 * Contiene información de fechas, horas, usuario y grupo.
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity (name="turns")
public class Turn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "initDate", nullable=false, length = 100)
    LocalDate initDate;

    @Column(name = "endDate", nullable=false, length = 100)
    LocalDate endDate;

    @Column(name = "userName", nullable=false, length = 100)
    String userName;

    @Column(name = "initHour", nullable=false, length = 100)
    String initHour;

    @Column(name = "endHour", nullable=false, length = 100)
    String endHour;

    @Column(name = "userId", nullable=false)
    Long userId;

    @Column(name = "groupId", nullable=false)
    Long groupId;
}
