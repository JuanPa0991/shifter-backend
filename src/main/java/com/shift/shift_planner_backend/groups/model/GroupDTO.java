package com.shift.shift_planner_backend.groups.model;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class GroupDTO {

    private Long id;
    private String name;
}
