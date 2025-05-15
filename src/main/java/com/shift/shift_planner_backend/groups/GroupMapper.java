package com.shift.shift_planner_backend.groups;


import com.shift.shift_planner_backend.groups.model.Group;
import com.shift.shift_planner_backend.groups.model.GroupDTO;

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
}
