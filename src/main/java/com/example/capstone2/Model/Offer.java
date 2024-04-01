package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_id")
    private Integer offerId;

    @NotNull(message = "Project ID cannot be empty")
    @Column(columnDefinition = "int not null", name = "project_id")
    private Integer projectId;

    @NotNull(message = "Investor ID cannot be empty")
    @Column(columnDefinition = "int not null", name = "investor_id")
    private Integer investorId;

    @Positive(message = "Amount must be positive")
    @Column(columnDefinition = "double not null check(amount > 0)")
    private Double amount;

    @Column(columnDefinition = "varchar(20) default 'pending'")
    private String status = "pending";

    @NotEmpty(message = "Document cannot be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String documentPath;
}
