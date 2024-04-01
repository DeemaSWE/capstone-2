package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Investor;
import com.example.capstone2.Model.Offer;
import com.example.capstone2.Model.Project;
import com.example.capstone2.Model.Student;
import com.example.capstone2.Repository.InvestorRepository;
import com.example.capstone2.Repository.OfferRepository;
import com.example.capstone2.Repository.ProjectRepository;
import com.example.capstone2.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferService {

    private final OfferRepository offerRepository;
    private final InvestorRepository investorRepository;
    private final ProjectRepository projectRepository;
    private final StudentRepository studentRepository;

    public List<Offer> getAllOffers(){
        return offerRepository.findAll();
    }

    // Investor can send funding offer to a student project
    public void sendOffer(Offer offer){
        Investor investor = investorRepository.findInvestorByInvestorId(offer.getInvestorId());
        if(investor == null)
            throw new ApiException("Investor with id " + offer.getInvestorId() + " not found");

        Project project = projectRepository.findProjectByProjectId(offer.getProjectId());
        if(project == null)
            throw new ApiException("Project with id " + offer.getProjectId() + " not found");

        if(offer.getAmount() > investor.getBalance())
            throw new ApiException("Investor with id " + offer.getInvestorId() + " does not have enough balance");

        //offer.setStatus("Pending");
        offerRepository.save(offer);
    }

    public void updateOffer(Offer updatedOffer, Integer id){
        Offer offer = offerRepository.findOfferByOfferId(id);
        if(offer == null)
            throw new ApiException("Offer with id " + id + " not found");

        Investor investor = investorRepository.findInvestorByInvestorId(updatedOffer.getInvestorId());
        if(investor == null)
            throw new ApiException("Investor with id " + updatedOffer.getInvestorId() + " not found");

        Project project = projectRepository.findProjectByProjectId(updatedOffer.getProjectId());
        if(project == null)
            throw new ApiException("Project with id " + updatedOffer.getProjectId() + " not found");

        offer.setDocumentPath(updatedOffer.getDocumentPath());
        offer.setAmount(updatedOffer.getAmount());

        offerRepository.save(offer);
    }

    public void deleteOffer(Integer id){
        Offer offer = offerRepository.findOfferByOfferId(id);

        if(offer == null)
            throw new ApiException("Offer with id " + id + " not found");

        offerRepository.delete(offer);
    }

    // Student can accept or reject an investor funding offer for a project
    public void setStatus(Integer offerId, String status){
        Offer offer = offerRepository.findOfferByOfferId(offerId);
        if(offer == null)
            throw new ApiException("Offer with id " + offerId + " not found");

        Investor investor = investorRepository.findInvestorByInvestorId(offer.getInvestorId());
        if(investor == null)
            throw new ApiException("Investor with id " + offer.getInvestorId() + " not found");

        Project project = projectRepository.findProjectByProjectId(offer.getProjectId());
        if(project == null)
            throw new ApiException("Project with id " + offer.getProjectId() + " not found");

        Student student = studentRepository.findStudentByStudentId(project.getStudentId());
        if(student == null)
            throw new ApiException("Student with id " + project.getStudentId() + " not found");

        if(!status.equalsIgnoreCase("approved") && !status.equalsIgnoreCase("rejected"))
            throw new ApiException("Status should be either approved or rejected");

        if(!offer.getStatus().equalsIgnoreCase("Pending"))
            throw new ApiException("Offer is already approved or rejected");

        if(status.equals("approved")){
            if(offer.getAmount() > investor.getBalance())
                throw new ApiException("Investor with id " + offer.getInvestorId() + " does not have enough balance");

            investor.setBalance(investor.getBalance() - offer.getAmount());
            investorRepository.save(investor);

            student.setBalance(student.getBalance() + offer.getAmount());
            studentRepository.save(student);

            project.setCurrentFunding(project.getCurrentFunding() + offer.getAmount());
            projectRepository.save(project);
        }

        offer.setStatus(status);
        offerRepository.save(offer);
    }

    // get all offers for a project
    public List<Offer> getOffersForProject(Integer projectId){
        Project project = projectRepository.findProjectByProjectId(projectId);
        if(project == null)
            throw new ApiException("Project with id " + projectId + " not found");

        List<Offer> offers = offerRepository.findOffersByProjectId(projectId);
        return offers;
    }

    // get all pending offers for a project
    public List<Offer> getPendingOffersForProject(Integer projectId){
        Project project = projectRepository.findProjectByProjectId(projectId);
        if(project == null)
            throw new ApiException("Project with id " + projectId + " not found");

        List<Offer> offers = offerRepository.findOffersByProjectIdAndStatus(projectId, "Pending");
        return offers;
    }


}
