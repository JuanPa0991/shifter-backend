package com.shift.shift_planner_backend.util;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FechaUtil {

    /**
     * Genera una lista de fechas entre dos fechas dadas, incluyendo ambas.
     *
     * @param fechaInicio Fecha de inicio (inclusive)
     * @param fechaFin    Fecha de fin (inclusive)
     * @return Lista de fechas entre fechaInicio y fechaFin
     */
    public static List<LocalDate> obtenerFechasEntre(LocalDate fechaInicio, LocalDate fechaFin) {
        return Stream.iterate(fechaInicio, fecha -> fecha.plusDays(1))
                .limit(ChronoUnit.DAYS.between(fechaInicio, fechaFin) + 1)
                .collect(Collectors.toList());
    }
}

