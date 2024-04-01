package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Integer projectId;

    @NotNull(message = "Student id cannot be empty")
    @Column(name = "student_id", columnDefinition = "int not null")
    private Integer studentId;

    @NotEmpty(message = "Title cannot be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String title;

    @NotEmpty(message = "Description cannot be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String description;

    @NotEmpty(message = "City cannot be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String city;

    @NotEmpty(message = "University cannot be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String university;

    @NotEmpty(message = "Project stage cannot be empty")
    @Pattern(regexp = "^(in_progress|completed)$", message = "Project stage should be in_progress or completed")
    private String projectStage;

    @PositiveOrZero(message = "Desired funding must be positive")
    @Column(columnDefinition = "double not null check(desired_funding >= 0)")
    private Double desiredFunding;

    @PositiveOrZero(message = "Current funding must be positive")
    @Column(columnDefinition = "double not null check(current_funding >= 0)")
    private Double currentFunding;

    @NotEmpty(message = "Category cannot be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String category;

    @Column(columnDefinition = "varchar(255)")
    private String imagePath;

    @Column(columnDefinition = "varchar(255)")
    private String videoPath;
}