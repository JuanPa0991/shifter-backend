package com.shift.shift_planner_backend.turns;

import com.shift.shift_planner_backend.turns.model.Turn;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Repository
public interface TurnRepository extends CrudRepository<Turn, Long> {
    Optional<Turn> findByInitDateAndEndDateAndUserName(
            LocalDate initDate,
            LocalDate endDate,
            String userName
    );
}
