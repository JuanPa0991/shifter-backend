package com.shift.shift_planner_backend.userNormal;


import com.shift.shift_planner_backend.userNormal.model.UserNormal;
import com.shift.shift_planner_backend.userNormal.model.UserNormalDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserNormalService {

    private final UserNormalRepository userNormalRepository;

    public UserNormalService(UserNormalRepository userNormalRepository) {
        this.userNormalRepository = userNormalRepository;
    }

    public UserNormalDTO createNormalUser (UserNormalDTO userNormalDTO) {
        userNormalRepository.findByEmail(
                userNormalDTO.getEmail()
        ).ifPresent(U ->{
            throw new RuntimeException("Ya existe un usuario con ese mail");
        });

        UserNormal userNormalMapped = UserNormalMapper.toEntity(userNormalDTO);
        UserNormal savedNormal = userNormalRepository.save(userNormalMapped);

        return UserNormalMapper.toDTO(savedNormal);
    }

    public List<UserNormalDTO> findAllDTOs() {
        Iterable<UserNormal> iterable = userNormalRepository.findAll();
        List<UserNormal> usersNormal = new ArrayList<>();
        iterable.forEach(usersNormal::add);
        return UserNormalMapper.toDTOs(usersNormal);
    }
}