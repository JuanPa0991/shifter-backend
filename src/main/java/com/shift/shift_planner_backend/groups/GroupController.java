package com.shift.shift_planner_backend.groups;

import com.shift.shift_planner_backend.groups.model.GroupDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class GroupController {

    private final GroupService groupService;

    GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    // @GetMapping("/group")
    // public ResponseEntity<List<GroupDTO>> findAll() {
    //     List<GroupDTO> dtos = groupService.findAllDTOs();
    //     return ResponseEntity
    //             .ok(dtos);
    // }

    @PostMapping("/group")
    public ResponseEntity<GroupDTO> createGroup(@RequestBody GroupDTO groupDTO) {
        return ResponseEntity.ok(groupService.createGroup(groupDTO));
    }

    @GetMapping("/groups")
    public ResponseEntity<List<GroupDTO>> findAll() {
        List<GroupDTO> dtos = groupService.findAllDTOs();
        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/groups/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        groupService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
