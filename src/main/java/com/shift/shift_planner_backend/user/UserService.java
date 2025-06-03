package com.shift.shift_planner_backend.user;


import com.shift.shift_planner_backend.commons.email.EmailService;
import com.shift.shift_planner_backend.commons.email.EmailTemplate;
import com.shift.shift_planner_backend.user.model.User;
import com.shift.shift_planner_backend.user.model.UserDTO;
import com.shift.shift_planner_backend.commons.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    //private final EmailService emailService;

    public UserService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        //this.emailService=emailService;
    }


    public UserDTO createUser (UserDTO userDTO) {

        userRepository.findByEmail(
                userDTO.getEmail()
        ).ifPresent(U ->{
            throw new RuntimeException("Ya existe un usuario con ese mail");
        });

        User userMapped = UserMapper.toEntity(userDTO); // se pasa el modelo dto a entidad
           String randomPassword = Utils.generateRandomString(12);
           userMapped.setPassword(randomPassword);

        User saved = userRepository.save(userMapped);

        var mailReplaces = new HashMap<String, String>() {{
            put("TEMP_PASSWORD", randomPassword);
            put("TOOL_URL", "http://localhost:4200/login");
            put("USER_MAIL", userDTO.getEmail());
        }};

        //emailService.sendTemplateEmail(EmailTemplate.USER_CREATED, userDTO.getEmail(), "Bienvenido a TurnoMaster!", mailReplaces);

        return UserMapper.toDTO(saved); //se pasa de entidad a dto
    }

    public List<UserDTO> findAllDTOs() {
        Iterable<User> iterable = userRepository.findAll();
        List<User> users = new ArrayList<>();
        iterable.forEach(users::add);
        return UserMapper.toDTOs(users);
    }

    public List<UserDTO> findByGroupId (Long groupId) {
        List<User>users = userRepository.findByGroupId(groupId);
        return  UserMapper.toDTOs(users);
    }
}
