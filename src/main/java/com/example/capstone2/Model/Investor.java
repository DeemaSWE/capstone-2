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
public class Investor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "investor_id")
    private Integer investorId;

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

    @PositiveOrZero(message = "Balance must be positive")
    @Column(columnDefinition = "double not null check(balance >= 0)")
    private Double balance;

    @ElementCollection
    @Column(name = "category_preference")
    private List<String> categoryPreference;

    @ElementCollection
    @Column(name = "university_preference")
    private List<String> universityPreference;

    @Positive(message = "Investment size preference must be positive")
    @Column(columnDefinition = "double not null check(investment_size_preference > 0)")
    private Double investmentSizePreference;
}
