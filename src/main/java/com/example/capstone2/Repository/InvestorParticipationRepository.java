package com.example.capstone2.Repository;

import com.example.capstone2.Model.Investor;
import com.example.capstone2.Model.InvestorParticipation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvestorParticipationRepository extends JpaRepository<InvestorParticipation, Integer> {

    InvestorParticipation findInvestorParticipationByParticipationId(Integer investorId);

    Boolean existsInvestorParticipationByInvestorIdAndEventId(Integer investorId, Integer eventId);
    List<InvestorParticipation> findInvestorParticipationByEventId(Integer eventId);
}