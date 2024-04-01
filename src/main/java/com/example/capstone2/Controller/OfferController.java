package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiResponse;
import com.example.capstone2.Model.Offer;
import com.example.capstone2.Service.OfferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/offer")
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @GetMapping("/get-all")
    public ResponseEntity getAllOffers(){
        return ResponseEntity.status(200).body(offerService.getAllOffers());
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateOffer(@PathVariable Integer id, @RequestBody @Valid Offer offer, Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        offerService.updateOffer(offer, id);
        return ResponseEntity.status(200).body(new ApiResponse("Offer updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOffer(@PathVariable Integer id){
        offerService.deleteOffer(id);
        return ResponseEntity.status(200).body(new ApiResponse("Offer deleted successfully"));
    }

    // Student can accept or reject an investor funding offer for a project
    @PutMapping("/set-status/{offerId}/{status}")
    public ResponseEntity setStatus(@PathVariable Integer offerId, @PathVariable String status){
        offerService.setStatus(offerId, status);
        return ResponseEntity.status(200).body(new ApiResponse("Status updated successfully"));
    }

    // Investor can send funding offer to a student project
    @PostMapping("/send")
    public ResponseEntity sendOffer(@RequestBody @Valid Offer offer, Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        offerService.sendOffer(offer);
        return ResponseEntity.status(200).body(new ApiResponse("Offer sent successfully"));
    }

    // get all offers for a project
    @GetMapping("/get-project-offers/{projectId}")
    public ResponseEntity getOffersForProject(@PathVariable Integer projectId){
        List<Offer> offers = offerService.getOffersForProject(projectId);

        if (offers.isEmpty())
            return ResponseEntity.status(404).body(new ApiResponse("No offers found for project with id " + projectId));

        return ResponseEntity.status(200).body(offers);
    }

    // get all pending offers for a project
    @GetMapping("/get-pending-offers/{projectId}")
    public ResponseEntity getPendingOffersForProject(@PathVariable Integer projectId){
        List<Offer> offers = offerService.getPendingOffersForProject(projectId);

        if (offers.isEmpty())
            return ResponseEntity.status(404).body(new ApiResponse("No pending offers found for project with id " + projectId));

        return ResponseEntity.status(200).body(offers);
    }

}