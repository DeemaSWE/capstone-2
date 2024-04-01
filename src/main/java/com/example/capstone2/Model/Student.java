package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer studentId;

    @NotEmpty(message = "Name cannot be empty")
    @Column(columnDefinition = "varchar(30) not null")
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

    @PositiveOrZero(message = "Balance must be positive")
    @Column(columnDefinition = "double not null check(balance >= 0)")
    private Double balance;

    @NotEmpty(message = "University cannot be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String university;

}
