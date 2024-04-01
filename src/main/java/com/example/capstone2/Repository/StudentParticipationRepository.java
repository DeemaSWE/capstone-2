package com.example.capstone2.Repository;

import com.example.capstone2.Model.InvestorParticipation;
import com.example.capstone2.Model.StudentParticipation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentParticipationRepository extends JpaRepository<StudentParticipation, Integer> {

    StudentParticipation findStudentParticipationByParticipationId(Integer participationId);

    Boolean existsStudentParticipationByStudentIdAndEventId(Integer studentId, Integer eventId);

    List<StudentParticipation> findStudentParticipationByEventId(Integer eventId);

}