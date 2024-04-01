package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Event;
import com.example.capstone2.Model.Investor;
import com.example.capstone2.Model.InvestorParticipation;
import com.example.capstone2.Repository.EventRepository;
import com.example.capstone2.Repository.InvestorParticipationRepository;
import com.example.capstone2.Repository.InvestorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvestorParticipationService {

    private final InvestorParticipationRepository investorParticipationRepository;
    private final InvestorRepository investorRepository;
    private final EventRepository eventRepository;

    public List<InvestorParticipation> getAllInvestorParticipation(){
        return investorParticipationRepository.findAll();
    }

    public void updateInvestorParticipation(InvestorParticipation updatedInvestorParticipation, Integer id){
        InvestorParticipation investorParticipation = investorParticipationRepository.findInvestorParticipationByParticipationId(id);
        if(investorParticipation == null)
            throw new ApiException("Investor participation with id " + id + " not found");

        Investor investor = investorRepository.findInvestorByInvestorId(updatedInvestorParticipation.getInvestorId());
        if(investor == null)
            throw new ApiException("Investor with id " + updatedInvestorParticipation.getInvestorId() + " not found");

        investorParticipation.setInvestorId(updatedInvestorParticipation.getInvestorId());

        investorParticipationRepository.save(investorParticipation);
    }

    public void deleteInvestorParticipation(Integer id){
        InvestorParticipation investorParticipation = investorParticipationRepository.findInvestorParticipationByParticipationId(id);
        if(investorParticipation == null)
            throw new ApiException("Investor participation with id " + id + " not found");

        investorParticipationRepository.delete(investorParticipation);
    }

    public void investorParticipateInEvent(InvestorParticipation investorParticipation){
        Investor investor = investorRepository.findInvestorByInvestorId(investorParticipation.getInvestorId());
        if(investor == null)
            throw new ApiException("Investor with id " + investorParticipation.getInvestorId() + " not found");

        Event event = eventRepository.findEventByEventId(investorParticipation.getEventId());
        if (event == null)
            throw new ApiException("Event with id " + investorParticipation.getEventId() + " not found");

        if(investorParticipationRepository.existsInvestorParticipationByInvestorIdAndEventId(investorParticipation.getInvestorId(), investorParticipation.getEventId()))
            throw new ApiException("Investor with id " + investorParticipation.getInvestorId() + " already participated in the event");

        investorParticipationRepository.save(investorParticipation);
    }

    public List<InvestorParticipation> getEventParticipants(Integer eventId){
        Event event = eventRepository.findEventByEventId(eventId);
        if (event == null)
            throw new ApiException("Event with id " + eventId + " not found");

        return investorParticipationRepository.findInvestorParticipationByEventId(eventId);
    }
}