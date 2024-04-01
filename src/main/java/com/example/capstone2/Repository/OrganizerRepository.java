package com.example.capstone2.Repository;

import com.example.capstone2.Model.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Integer> {

    Organizer findOrganizerByOrganizerId(Integer id);
}
