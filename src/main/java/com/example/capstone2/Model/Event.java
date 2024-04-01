// Event.java
package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eventId;

    @NotNull(message = "Organizer id cannot be empty")
    @Column(name = "organizer_id", columnDefinition = "int not null")
    private Integer organizerId;

    @NotEmpty(message = "Event name cannot be empty")
    @Column( columnDefinition = "varchar(100) not null")
    private String name;

    @NotEmpty(message = "Event description cannot be empty")
    @Column(columnDefinition = "varchar(200) not null")
    private String description;

    @NotEmpty(message = "Event location cannot be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String location;

    @PositiveOrZero(message = "Max projects allowed must be positive")
    @Column(columnDefinition = "int not null check(max_projects_allowed >= 0)", name = "max_projects_allowed")
    private Integer maxProjectsAllowed;

    @NotNull(message = "Event date cannot be empty")
    @Column(columnDefinition = "date not null")
    private LocalDate eventDate;
}