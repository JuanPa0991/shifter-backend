package com.shift.shift_planner_backend.userNormal.model;

import lombok.*;


@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor

public class UserNormalDTO {

    private Long id;
    private String name;
    private String lastName;
    private String dni;
    private String email;
    private String companyName;


}
