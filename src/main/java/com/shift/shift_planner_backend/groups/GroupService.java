package com.shift.shift_planner_backend.groups;


import com.shift.shift_planner_backend.groups.model.Group;
import com.shift.shift_planner_backend.groups.model.GroupDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {

    private  final GroupRepository groupRepository;

    public GroupService (GroupRepository groupRepository) {
        this.groupRepository=groupRepository;
    }

    public GroupDTO createGroup (GroupDTO groupDTO) {
        groupRepository.findByName(
                groupDTO.getName()
        ).ifPresent( g ->{
            throw new RuntimeException("Ya existe un grupo con eser nombre");
        });

        Group groupMapped = GroupMapper.toEntity((groupDTO));
        Group saved = groupRepository.save(groupMapped);

        return  GroupMapper.toDTO(saved);
    }

    public List<GroupDTO> findAllDTOs() {

        Iterable<Group> iterable = groupRepository.findAll();
        List<Group> groups = new ArrayList<>();
        iterable.forEach(groups::add);

        return GroupMapper.toDTOs(groups);
    }


}
