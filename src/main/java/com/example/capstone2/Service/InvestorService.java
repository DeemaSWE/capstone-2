package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Investor;
import com.example.capstone2.Model.Offer;
import com.example.capstone2.Model.Project;
import com.example.capstone2.Repository.InvestorRepository;
import com.example.capstone2.Repository.OfferRepository;
import com.example.capstone2.Repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvestorService {

    private final InvestorRepository investorRepository;
    private final ProjectRepository projectRepository;
    private final OfferRepository offerRepository;

    public List<Investor> getAllInvestors(){
        return investorRepository.findAll();
    }

    public void addInvestor(Investor investor){
        String email = investor.getEmail();
        if(investorRepository.existsInvestorByEmail(email))
            throw new ApiException("Investor with email " + email + " already exists");

        investorRepository.save(investor);
    }

    public void addListInvestors(List<Investor> investors){
        investorRepository.saveAll(investors);
    }

    public void updateInvestor(Investor updatedInvestor, Integer id){
        Investor investor = investorRepository.findInvestorByInvestorId(id);

        if (investor == null)
            throw new ApiException("Investor with id " + id + " not found");

        String updatedEmail = updatedInvestor.getEmail();
        if(investorRepository.existsInvestorByEmail(updatedEmail) && !investor.getEmail().equals(updatedEmail))
            throw new ApiException("Investor with email " + updatedEmail + " already exists");

        investor.setEmail(updatedInvestor.getEmail());
        investor.setPassword(updatedInvestor.getPassword());
        investor.setName(updatedInvestor.getName());
        investor.setPhoneNumber(updatedInvestor.getPhoneNumber());
        investor.setBalance(updatedInvestor.getBalance());
        investor.setCategoryPreference(updatedInvestor.getCategoryPreference());
        investor.setUniversityPreference(updatedInvestor.getUniversityPreference());
        investor.setInvestmentSizePreference(updatedInvestor.getInvestmentSizePreference());

        investorRepository.save(investor);
    }

    public void deleteInvestor(Integer id){
        Investor investor = investorRepository.findInvestorByInvestorId(id);

        if (investor == null)
            throw new ApiException("Investor with id " + id + " not found");

        investorRepository.delete(investor);
    }

    // get recommend projects for investor based on investor preferences
    public List<Project> recommendProjectsForInvestor(Integer investorId) {
        Investor investor = investorRepository.findInvestorByInvestorId(investorId);

        if (investor == null)
            throw new ApiException("Investor with id " + investorId + " not found");

        List<Project> recommendedProjects = projectRepository.findProjectsByCategoryInAndUniversityInAndDesiredFundingLessThanEqual(
                investor.getCategoryPreference(), investor.getUniversityPreference(), investor.getInvestmentSizePreference());

        return recommendedProjects;
    }


}