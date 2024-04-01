package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiResponse;
import com.example.capstone2.Model.Event;
import com.example.capstone2.Model.Investor;
import com.example.capstone2.Model.Student;
import com.example.capstone2.Service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping("/get-all")
    public ResponseEntity getAllEvents() {
        return ResponseEntity.status(200).body(eventService.getAllEvents());
    }

    @PostMapping("/add")
    public ResponseEntity addEvent(@RequestBody @Valid Event event, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        eventService.addEvent(event);
        return ResponseEntity.status(200).body(new ApiResponse("Event added successfully"));
    }

    @PostMapping("/add-list")
    public ResponseEntity addEvents(@RequestBody @Valid List<Event> events, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        eventService.addListEvents(events);
        return ResponseEntity.status(200).body(new ApiResponse("Events added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateEvent(@PathVariable Integer id, @RequestBody @Valid Event event, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        eventService.updateEvent(event, id);
        return ResponseEntity.status(200).body(new ApiResponse("Event updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEvent(@PathVariable Integer id) {
        eventService.deleteEvent(id);
        return ResponseEntity.status(200).body(new ApiResponse("Event deleted successfully"));
    }

    // get a list of upcoming events based on the current date.
    @GetMapping("/upcoming")
    public ResponseEntity getUpcomingEvents() {
        List<Event> eventsList = eventService.getUpcomingEvents();

        if (eventsList.isEmpty())
            return ResponseEntity.status(404).body(new ApiResponse("No upcoming events found"));

        return ResponseEntity.ok(eventsList);
    }


}