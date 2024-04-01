package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiResponse;
import com.example.capstone2.Model.Investor;
import com.example.capstone2.Model.InvestorParticipation;
import com.example.capstone2.Service.InvestorParticipationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/investor/event")
@RequiredArgsConstructor
public class InvestorParticipationController {

    private final InvestorParticipationService investorParticipationService;

    @GetMapping("/get-all")
    public ResponseEntity getAllInvestorParticipation(){
        return ResponseEntity.status(200).body(investorParticipationService.getAllInvestorParticipation());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateInvestorParticipation(@PathVariable Integer id, @RequestBody @Valid InvestorParticipation investorParticipation, Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        investorParticipationService.updateInvestorParticipation(investorParticipation, id);
        return ResponseEntity.status(200).body(new ApiResponse("Investor Participation updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteInvestorParticipation(@PathVariable Integer id){
        investorParticipationService.deleteInvestorParticipation(id);
        return ResponseEntity.status(200).body(new ApiResponse("Investor Participation deleted successfully"));
    }

    // investor can participate in an event
    @PostMapping("/participate")
    public ResponseEntity investorParticipateInEvent(@RequestBody @Valid InvestorParticipation investorParticipation, Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        investorParticipationService.investorParticipateInEvent(investorParticipation);
        return ResponseEntity.status(200).body(new ApiResponse("Investor successfully participated in event"));
    }

    // get all investor participants for an event
    @GetMapping("/get-participants/{eventId}")
    public ResponseEntity getEventParticipants(@PathVariable Integer eventId){
        List<InvestorParticipation> participants = investorParticipationService.getEventParticipants(eventId);

        if (participants.isEmpty())
            return ResponseEntity.status(404).body(new ApiResponse("No participants found for event"));

        return ResponseEntity.status(200).body(participants);
    }
}