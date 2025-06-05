package com.shift.shift_planner_backend.turns.specifications;

import com.shift.shift_planner_backend.turns.model.Turn;
import com.shift.shift_planner_backend.turns.model.TurnFilterDTO;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase construye dinámicamente una Specification de JPA para aplicar
 * filtros personalizados a la entidad Turn según los valores recibidos en TurnFilterDTO.
 */
public class TurnSpecification {

    public static Specification<Turn> filterBy(TurnFilterDTO filter) {
        return (Root<Turn> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            // Lista de condiciones (predicados) que se irán añadiendo según los filtros presentes
            List<Predicate> predicates = new ArrayList<>();

            // Si se especifica una fecha de inicio, se añade un filtro para que sea >= initDate
            if (filter.getInitDate() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("initDate"), filter.getInitDate()));
            }

            // Si se especifica una fecha de fin, se añade un filtro para que sea <= endDate
            if (filter.getEndDate() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("endDate"), filter.getEndDate()));
            }

            // Filtro por nombre de usuario si no está vacío
            if (filter.getUserName() != null && !filter.getUserName().isBlank()) {
                // Se hace búsqueda que ignore mayúsculas/minúsculas
                predicates.add(cb.like(cb.lower(root.get("userName")), "%" + filter.getUserName().toLowerCase() + "%"));
            }

            // Filtro exacto por ID de usuario si está definido
            if (filter.getUserId() != null) {
                predicates.add(cb.equal(root.get("userId"), filter.getUserId()));
            }

            // Filtro por hora de inicio exacta si está definida
            if (filter.getInitHour() != null && !filter.getInitHour().isBlank()) {
                predicates.add(cb.equal(root.get("initHour"), filter.getInitHour()));
            }

            // Filtro por hora de fin exacta si está definida
            if (filter.getEndHour() != null && !filter.getEndHour().isBlank()) {
                predicates.add(cb.equal(root.get("endHour"), filter.getEndHour()));
            }

            // Filtro por grupo si está definido
            if (filter.getGroupId() != null) {
                predicates.add(cb.equal(root.get("groupId"), filter.getGroupId()));
            }

            // Combina todas las condiciones con AND para formar la cláusula WHERE final
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
