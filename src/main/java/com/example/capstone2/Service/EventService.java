package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.*;
import com.example.capstone2.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final OrganizerRepository organizerRepository;
    private final StudentRepository studentRepository;
    private final ProjectRepository projectRepository;
    private final InvestorRepository investorRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public void addEvent(Event event) {
        Organizer organizer = organizerRepository.findOrganizerByOrganizerId(event.getOrganizerId());

        if (organizer == null)
            throw new ApiException("Organizer with id " + event.getOrganizerId() + " not found");

        eventRepository.save(event);
    }

    public void addListEvents(List<Event> events) {
        eventRepository.saveAll(events);
    }

    public void updateEvent(Event updatedEvent, Integer id) {
        Event event = eventRepository.findEventByEventId(id);
        Organizer organizer = organizerRepository.findOrganizerByOrganizerId(updatedEvent.getOrganizerId());

        if (event == null)
            throw new ApiException("Event with id " +id + " not found");

        if (organizer == null)
            throw new ApiException("Organizer with id " + updatedEvent.getOrganizerId()+ " not found");

        event.setName(updatedEvent.getName());
        event.setDescription(updatedEvent.getDescription());
        event.setEventDate(updatedEvent.getEventDate());
        event.setLocation(updatedEvent.getLocation());
        event.setMaxProjectsAllowed(updatedEvent.getMaxProjectsAllowed());
        event.setOrganizerId(updatedEvent.getOrganizerId());

        eventRepository.save(event);
    }

    public void deleteEvent(Integer id) {
        Event event = eventRepository.findEventByEventId(id);

        if (event == null)
            throw new ApiException("Event with id " + id + " not found");

        eventRepository.delete(event);
    }

    // get a list of upcoming events based on the current date.
    public List<Event> getUpcomingEvents() {
        return eventRepository.findEventsByEventDateAfter(LocalDate.now());
    }

    // filter events by date range
    public List<Event> filterEventsByDate(LocalDate start, LocalDate end) {
        return eventRepository.findEventsByEventDateBetween(start, end);
    }
}
