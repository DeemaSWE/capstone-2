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
public class InvestorParticipation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participation_id")
    private Integer participationId;

    @NotNull(message = "Investor ID cannot be empty")
    @Column(name = "investor_id", columnDefinition = "int not null")
    private Integer investorId;

    @NotNull(message = "Event ID cannot be empty")
    @Column(name = "event_id", columnDefinition = "int not null")
    private Integer eventId;
}
