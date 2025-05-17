package com.shift.shift_planner_backend.groups;

import com.shift.shift_planner_backend.groups.model.GroupDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class GroupController {

    private final GroupService groupService;

    GroupController (GroupService groupService) {this.groupService=groupService;}

    @PostMapping ("/group")
    public ResponseEntity<GroupDTO> createGroup (@RequestBody GroupDTO groupDTO){
        return  ResponseEntity.ok(groupService.createGroup(groupDTO));
    }

    @GetMapping("/group")
    public ResponseEntity<List<GroupDTO>> findAll() {
        List<GroupDTO> dtos = groupService.findAllDTOs();
        return ResponseEntity
                .ok(dtos);
    }

}
