package com.shift.shift_planner_backend.userNormal;

import com.shift.shift_planner_backend.userNormal.model.UserNormal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserNormalRepository extends CrudRepository<UserNormal, Long> {
    Optional<UserNormal> findByEmail(String email);
}
