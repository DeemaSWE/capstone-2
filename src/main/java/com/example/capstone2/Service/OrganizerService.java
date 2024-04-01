package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Organizer;
import com.example.capstone2.Repository.OrganizerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizerService {

    private final OrganizerRepository organizerRepository;

    public List<Organizer> getAllOrganizers() {
        return organizerRepository.findAll();
    }

    public void addOrganizer(Organizer organizer) {
        organizerRepository.save(organizer);
    }

    public void addListOrganizers(List<Organizer> organizers) {
        organizerRepository.saveAll(organizers);
    }

    public void updateOrganizer(Organizer updatedOrganizer, Integer id) {
        Organizer organizer = organizerRepository.findOrganizerByOrganizerId(id);

        if (organizer == null)
            throw new ApiException("Organizer with id " + id + " not found");

        organizer.setName(updatedOrganizer.getName());
        organizer.setEmail(updatedOrganizer.getEmail());

        organizerRepository.save(organizer);
    }

    public void deleteOrganizer(Integer id) {
        Organizer organizer = organizerRepository.findOrganizerByOrganizerId(id);

        if (organizer == null)
            throw new ApiException("Organizer with id " + id + " not found");

        organizerRepository.delete(organizer);
    }
}