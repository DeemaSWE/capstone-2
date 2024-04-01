package com.example.capstone2.Repository;

import com.example.capstone2.Model.Investor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvestorRepository extends JpaRepository<Investor, Integer>{

    Investor findInvestorByInvestorId(Integer id);
    Boolean existsInvestorByEmail(String email);

    List<Investor> findInvestorsByInvestorIdIn(List<Integer> investorIds);


}