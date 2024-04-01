package com.example.capstone2.Repository;

import com.example.capstone2.Model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    Project findProjectByProjectId(Integer id);

    List<Project> findProjectsByCategoryInAndUniversityInAndDesiredFundingLessThanEqual(List<String> categoriesPreference, List<String> universityPreference, Double investmentSizePreference);

    List<Project> findProjectsByDesiredFundingBetween(Double minFunding, Double maxFunding);

    @Query("SELECT p FROM Project p WHERE p.title LIKE %:keyword% OR p.description LIKE %:keyword% OR p.category LIKE %:keyword% OR p.university LIKE %:keyword% OR p.city LIKE %:keyword%")
    List<Project> searchProjectByKeyword(String keyword);

    List<Project> findProjectsByCategory(String category);

    List<Project> findProjectsByUniversity(String university);

    List<Project> findProjectsByStudentId(Integer studentId);

}
