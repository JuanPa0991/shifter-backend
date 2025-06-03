package com.shift.shift_planner_backend.user.model;

import lombok.*;


@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String lastName;
    private String dni;
    private String email;
    private String companyName;
    private Boolean isAdmin;
    private Long groupId;


}
