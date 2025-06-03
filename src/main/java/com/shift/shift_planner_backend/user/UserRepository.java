package com.shift.shift_planner_backend.user;

import org.springframework.stereotype.Repository;

import com.shift.shift_planner_backend.user.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByGroupId (Long groupId);
}
