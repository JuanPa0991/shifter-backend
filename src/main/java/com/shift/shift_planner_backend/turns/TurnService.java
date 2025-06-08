package com.shift.shift_planner_backend.turns;

import com.shift.shift_planner_backend.turns.model.Turn;
import com.shift.shift_planner_backend.turns.model.TurnDTO;
import com.shift.shift_planner_backend.turns.model.TurnFilterDTO;
import com.shift.shift_planner_backend.turns.model.TurnoMasivoDTO;
import com.shift.shift_planner_backend.turns.specifications.TurnSpecification;
import com.shift.shift_planner_backend.user.UserRepository;
import com.shift.shift_planner_backend.user.UserService;
import com.shift.shift_planner_backend.user.model.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.shift.shift_planner_backend.util.FechaUtil.obtenerFechasEntre;

/**
 * Servicio principal para gestionar turnos.
 * Incluye creación individual, masiva por grupo o usuarios,
 * y filtrado dinámico de turnos por criterios personalizados.
 */
@Service
public class TurnService {

    private final TurnRepository turnRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    /**
     * Constructor con inyección de dependencias para repositorio de turnos, usuarios y servicio de usuarios.
     *
     * @param turnRepository  Repositorio de persistencia para Turn
     * @param userRepository  Repositorio de persistencia para User
     * @param userService     Servicio de usuarios
     */
    public TurnService(TurnRepository turnRepository, UserRepository userRepository, UserService userService) {
        this.turnRepository = turnRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    /**
     * Crea un turno individual, asegurando que no se repita uno con las mismas fechas y usuario.
     *
     * @param createTurnDTO DTO con la información del turno a crear
     * @return TurnDTO del turno guardado
     * @throws RuntimeException si ya existe un turno con los mismos datos
     */
    public TurnDTO createTurn(TurnDTO createTurnDTO) {
        turnRepository.findByInitDateAndEndDateAndUserName(
                createTurnDTO.getInitDate(),
                createTurnDTO.getEndDate(),
                createTurnDTO.getUserName()
        ).ifPresent(t -> {
            throw new RuntimeException("Ya existe un turno con esos datos.");
        });

        Turn turnMapped = TurnMapper.toEntity((createTurnDTO));
        Turn saved = turnRepository.save(turnMapped);

        return TurnMapper.toDTO(saved);
    }

    /**
     * Crea turnos para todos los usuarios de un grupo, a partir de un template de datos comunes.
     *
     * @param groupId   ID del grupo cuyos miembros recibirán el turno
     * @param turnData  Información común para todos los turnos
     * @return Lista de turnos creados convertidos a DTO
     */
    public List<TurnDTO> createTurnsByGroup(Long groupId, TurnDTO turnData) {
        List<UserDTO> usersInGroup = userService.findByGroupId(groupId);
        List<Turn> turns = new ArrayList<>();

        for (UserDTO user : usersInGroup) {
            Turn turn = TurnMapper.toEntity(turnData);
            turn.setUserId(user.getId());
            turn.setUserName(user.getName());
            turn.setGroupId(groupId);
            turns.add(turnRepository.save(turn));
        }

        return turns.stream().map(TurnMapper::toDTO).collect(Collectors.toList());
    }

    /**
     * Filtra turnos usando especificaciones dinámicas basadas en el DTO de filtros.
     * Ideal para construir búsquedas personalizadas en el frontend.
     *
     * @param filter DTO con los criterios de filtrado (fechas, usuario, grupo, etc.)
     * @return Lista de turnos que cumplen con los criterios
     */
    public List<TurnDTO> filterTurns(TurnFilterDTO filter) {
        /*Llama al repositorio con la especificación generada en TurnSpecification,
         aplicando los filtros dinámicos definidos por el usuario.*/
        List<Turn> results = turnRepository.findAll(TurnSpecification.filterBy(filter));

        /* Convierte cada entidad Turn obtenida de la base de datos en un objeto TurnDTO
        para devolver una lista apta para enviar al frontend.*/
        return results.stream()
                .map(TurnMapper::toDTO) // Aplica la conversión entidad -> DTO
                .collect(Collectors.toList()); // Recolecta todo en una lista
    }

    /**
     * Crea turnos masivos para múltiples usuarios, permitiendo turnos normales o que cruzan la medianoche.
     *
     * - Si horaFin < horaInicio:
     *    - Si fechaInicio == fechaFin → se crea un solo turno (initDate, endDate = initDate + 1)
     *    - Si fechaInicio < fechaFin → se crea un turno por día, cada uno cruza a la noche siguiente.
     *
     * - Si horaFin > horaInicio:
     *    - Se crean turnos normales por día, initDate = endDate
     *
     * @param dto Objeto que contiene los datos del turno masivo
     */
    public void crearTurnosMasivos(TurnoMasivoDTO dto) {
        List<UserDTO> usuariosDTO;

        // Determina los usuarios a los que se asignarán turnos
        if (dto.getIdsUsuarios() != null && !dto.getIdsUsuarios().isEmpty()) {
            usuariosDTO = dto.getIdsUsuarios().stream()
                    .map(userService::getUserById)
                    .collect(Collectors.toList());
        } else if (dto.getIdGrupo() != null) {
            usuariosDTO = userService.findByGroupId(dto.getIdGrupo());
        } else {
            throw new RuntimeException("Debe proporcionar IDs de usuarios o un ID de grupo.");
        }

        boolean cruzaNoche = dto.getHoraFin().isBefore(dto.getHoraInicio());

        // Caso 1: Turno que cruza medianoche en un solo día (ej. 06/06, 22:00–06:00)
        if (dto.getFechaInicio().equals(dto.getFechaFin()) && cruzaNoche) {
            for (UserDTO user : usuariosDTO) {
                Turn turno = buildTurn(user, dto.getFechaInicio(), dto.getFechaInicio().plusDays(1), dto);
                turnRepository.save(turno);
            }
        }

        // Caso 2: Turnos que cruzan noche por varios días (ej. 06/06–08/06, 22:00–06:00)
        else if (dto.getFechaInicio().isBefore(dto.getFechaFin()) && cruzaNoche) {
            List<LocalDate> fechas = obtenerFechasEntre(dto.getFechaInicio(), dto.getFechaFin());
            for (UserDTO user : usuariosDTO) {
                for (LocalDate fecha : fechas) {
                    Turn turno = buildTurn(user, fecha, fecha.plusDays(1), dto);
                    turnRepository.save(turno);
                }
            }
        }

        // Caso 3: Turnos normales dentro del mismo día
        else {
            List<LocalDate> fechas = obtenerFechasEntre(dto.getFechaInicio(), dto.getFechaFin());
            for (UserDTO user : usuariosDTO) {
                for (LocalDate fecha : fechas) {
                    Turn turno = buildTurn(user, fecha, fecha, dto);
                    turnRepository.save(turno);
                }
            }
        }
    }

    /**
     * Construye un objeto Turn para un usuario con fechas y horas específicas.
     *
     * @param user     Usuario al que se le asigna el turno
     * @param initDate Fecha de inicio del turno
     * @param endDate  Fecha de fin del turno
     * @param dto      Datos comunes del turno (horas, grupo, etc.)
     * @return Objeto Turn listo para guardar
     */
    private Turn buildTurn(UserDTO user, LocalDate initDate, LocalDate endDate, TurnoMasivoDTO dto) {
        return Turn.builder()
                .userId(user.getId())
                .userName(user.getName())
                .groupId(user.getGroupId())
                .initDate(initDate)
                .endDate(endDate)
                .initHour(dto.getHoraInicio().toString())
                .endHour(dto.getHoraFin().toString())
                .build();
    }

        //return turns.stream().map(TurnMapper::toDTO).collect(Collectors.toList());
    //}
    public List<TurnDTO> findAllDTOs() {
        Iterable<Turn> iterable = turnRepository.findAll();
        List<Turn> turns = new ArrayList<>();
        iterable.forEach(turns::add);
        return turns.stream().map(TurnMapper::toDTO).toList();
    }

    public void deleteById(Long id) {
        turnRepository.deleteById(id);
    }
    /**
     * Elimina turnos que coincidan con los filtros especificados.
     * Utiliza especificaciones dinámicas como en el filtrado.
     *
     * @param filter Filtros que definen qué turnos serán eliminados
     */
    public void deleteTurnsByFilter(TurnFilterDTO filter) {
        List<Turn> turnsToDelete = turnRepository.findAll(TurnSpecification.filterBy(filter));
        if (turnsToDelete.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se encontraron turnos para eliminar.");
        }
        turnRepository.deleteAll(turnsToDelete);
    }
    /**
     * Elimina todos los turnos sin aplicar filtros.
     * Solo para el rol de Admin.
     */
    public void deleteAllTurns() {
        turnRepository.deleteAll();
    }

    /*
     * Comentario: Este método (createTurnsByUsers) está comentado pero tiene potencial para ser reactivado si
     * se desea crear turnos para una lista específica de usuarios con turnos personalizados.
     * También podría extenderse para incluir lógica condicional o validaciones por usuario.
     */
}
