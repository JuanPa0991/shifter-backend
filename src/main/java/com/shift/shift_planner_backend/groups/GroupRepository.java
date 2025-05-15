package com.shift.shift_planner_backend.groups;



import com.shift.shift_planner_backend.groups.model.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {
    Optional<Group>findByName(String groupName);
}
