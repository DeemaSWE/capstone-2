package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiResponse;
import com.example.capstone2.Model.Investor;
import com.example.capstone2.Model.Project;
import com.example.capstone2.Service.InvestorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/investor")
@RequiredArgsConstructor
public class InvestorController {

    private final InvestorService investorService;

    @GetMapping("/get-all")
    public ResponseEntity getAllInvestors(){
        return ResponseEntity.status(200).body(investorService.getAllInvestors());
    }

    @PostMapping("/add")
    public ResponseEntity addInvestor(@RequestBody @Valid Investor investor, Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        investorService.addInvestor(investor);
        return ResponseEntity.status(200).body(new ApiResponse("Investor added successfully"));
    }

    @PostMapping("/add-list")
    public ResponseEntity addInvestors(@RequestBody @Valid List<Investor> investors, Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        investorService.addListInvestors(investors);
        return ResponseEntity.status(200).body(new ApiResponse("Investors added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateInvestor(@PathVariable Integer id, @RequestBody @Valid Investor investor, Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        investorService.updateInvestor(investor, id);
        return ResponseEntity.status(200).body(new ApiResponse("Investor updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteInvestor(@PathVariable Integer id){
        investorService.deleteInvestor(id);
        return ResponseEntity.status(200).body(new ApiResponse("Investor deleted successfully"));
    }

    // get personalized recommendations for projects based on investor preferences
    @GetMapping("/project-recommendations/{investorId}")
    public ResponseEntity getProjectRecommendations(@PathVariable Integer investorId) {
        List<Project> recommendedProjects = investorService.recommendProjectsForInvestor(investorId);

        if (recommendedProjects.isEmpty())
            return ResponseEntity.status(404).body(new ApiResponse("No projects found for investor"));

        return ResponseEntity.ok(recommendedProjects);
    }


}
