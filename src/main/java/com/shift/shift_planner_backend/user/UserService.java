package com.shift.shift_planner_backend.user;

import com.shift.shift_planner_backend.commons.email.EmailService;
import com.shift.shift_planner_backend.commons.email.EmailTemplate;
import com.shift.shift_planner_backend.commons.utils.Utils;
import com.shift.shift_planner_backend.user.model.User;
import com.shift.shift_planner_backend.user.model.UserDTO;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    public UserService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public List<UserDTO> findAllDTOs() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(UserMapper::toDTO)
                .toList();
    }

    @Transactional
    public UserDTO createUser (UserDTO userDTO) {

        userRepository.findByEmail(
                userDTO.getEmail()
        ).ifPresent(U -> {
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

        emailService.sendTemplateEmail(EmailTemplate.USER_CREATED, userDTO.getEmail(), "Bienvenido a TurnoMaster!", mailReplaces);

        return UserMapper.toDTO(saved); //se pasa de entidad a dto
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public List<UserDTO> findByGroupId (Long groupId) {
        List<User>users = userRepository.findByGroupId(groupId);
        return  UserMapper.toDTOs(users);
    }

    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado con ID: " + id));
    }

}
