package com.shift.shift_planner_backend.groups;


import com.shift.shift_planner_backend.groups.model.Group;
import com.shift.shift_planner_backend.groups.model.GroupDTO;

import java.util.ArrayList;
import java.util.List;

public class GroupMapper {

    public static Group toEntity (GroupDTO groupDTO) {

        return Group.builder()
                .id(groupDTO.getId())
                .name(groupDTO.getName())
                .build();
    }

    public static GroupDTO toDTO (Group group) {

        return GroupDTO.builder()
                .id(group.getId())
                .name(group.getName())
                .build();
    }

    public static List<GroupDTO> toDTOs(List<Group> groups) {
        List<GroupDTO> dtos = new ArrayList<>(groups.size());
        for (Group group : groups) {
            dtos.add(toDTO(group));
        }
        return dtos;
    }
}
