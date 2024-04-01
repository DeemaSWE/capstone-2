package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Organizer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer organizerId;

    @NotEmpty(message = "Name cannot be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String name;

    @Email(message = "Email should be valid")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    @Column(columnDefinition = "varchar(60) not null")
    private String password;

    @NotEmpty(message = "Phone number cannot be empty")
    @Pattern(regexp = "^05\\d{8}$", message = "Phone number should be valid")
    @Column(columnDefinition = "varchar(10) not null")
    private String phoneNumber;
}
