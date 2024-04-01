// OrganizerController.java
package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiResponse;
import com.example.capstone2.Model.Organizer;
import com.example.capstone2.Service.OrganizerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/organizer")
@RequiredArgsConstructor
public class OrganizerController {

    private final OrganizerService organizerService;

    @GetMapping("/get-all")
    public ResponseEntity getAllOrganizers() {
        return ResponseEntity.status(200).body(organizerService.getAllOrganizers());
    }

    @PostMapping("/add")
    public ResponseEntity addOrganizer(@RequestBody @Valid Organizer organizer, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        organizerService.addOrganizer(organizer);
        return ResponseEntity.status(200).body(new ApiResponse("Organizer added successfully"));
    }

    @PostMapping("/add-list")
    public ResponseEntity addOrganizers(@RequestBody @Valid List<Organizer> organizers, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        organizerService.addListOrganizers(organizers);
        return ResponseEntity.status(200).body(new ApiResponse("Organizers added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateOrganizer(@PathVariable Integer id, @RequestBody @Valid Organizer organizer, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        organizerService.updateOrganizer(organizer, id);
        return ResponseEntity.status(200).body(new ApiResponse("Organizer updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrganizer(@PathVariable Integer id) {
        organizerService.deleteOrganizer(id);
        return ResponseEntity.status(200).body(new ApiResponse("Organizer deleted successfully"));
    }
}