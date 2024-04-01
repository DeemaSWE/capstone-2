package com.example.capstone2.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StudentParticipation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participation_id")
    private Integer participationId;

    @NotNull(message = "Student ID cannot be empty")
    @Column(name = "student_id", columnDefinition = "int not null")
    private Integer studentId;

    @NotNull(message = "Event ID cannot be empty")
    @Column(name = "event_id", columnDefinition = "int not null")
    private Integer eventId;

    @NotNull(message = "Project ID cannot be empty")
    @Column(name = "project_id", columnDefinition = "int not null")
    private Integer projectId;

}