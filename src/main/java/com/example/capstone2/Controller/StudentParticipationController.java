package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiResponse;
import com.example.capstone2.Model.StudentParticipation;
import com.example.capstone2.Service.StudentParticipationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student/event")
@RequiredArgsConstructor
public class StudentParticipationController {

    private final StudentParticipationService studentParticipationService;

    @GetMapping("/get-all")
    public ResponseEntity getAllStudentParticipation(){
        return ResponseEntity.status(200).body(studentParticipationService.getAllStudentParticipation());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStudentParticipation(@PathVariable Integer id, @RequestBody @Valid StudentParticipation studentParticipation, Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        studentParticipationService.updateStudentParticipation(studentParticipation, id);
        return ResponseEntity.status(200).body(new ApiResponse("Student Participation updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudentParticipation(@PathVariable Integer id){
        studentParticipationService.deleteStudentParticipation(id);
        return ResponseEntity.status(200).body(new ApiResponse("Student Participation deleted successfully"));
    }

    // student can participate in an event
    @PostMapping("/participate")
    public ResponseEntity studentParticipateInEvent(@RequestBody @Valid StudentParticipation studentParticipation, Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        studentParticipationService.studentParticipateInEvent(studentParticipation);
        return ResponseEntity.status(200).body(new ApiResponse("Student successfully participated in event"));
    }

    // get all student participants for an event
    @GetMapping("/get-participants/{eventId}")
    public ResponseEntity getEventParticipants(@PathVariable Integer eventId){
        List<StudentParticipation> participants = studentParticipationService.getEventParticipants(eventId);

        if (participants.isEmpty())
            return ResponseEntity.status(404).body(new ApiResponse("No participants found for event"));

        return ResponseEntity.status(200).body(participants);
    }

}