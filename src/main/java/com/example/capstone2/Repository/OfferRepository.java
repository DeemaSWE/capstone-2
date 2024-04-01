package com.example.capstone2.Repository;

import com.example.capstone2.Model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
    Offer findOfferByOfferId(Integer id);

    List<Offer> findOffersByProjectId(Integer projectId);

    List<Offer> findOffersByInvestorId(Integer investorId);

    List<Offer> findOffersByProjectIdAndStatus(Integer projectId, String status);
}
