package com.shift.shift_planner_backend.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="\"user\"")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", nullable = false, length = 100)
    String name;

    @Column(name = "lastName", nullable = false, length = 100)
    String lastName;

    @Column(name = "dni", nullable = false, length = 100)
    String dni;
    

    @Column(name = "companyName", nullable = false, length = 100)
    String companyName;
    

    @Column(name = "email", nullable = false, length = 100)
    String email;

    @Column(name = "password", nullable = false, length = 100)
    String password;

    @Column(name = "isAdmin", nullable = false, length = 100)
    Boolean isAdmin;

}
