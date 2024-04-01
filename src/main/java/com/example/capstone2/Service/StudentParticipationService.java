package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Event;
import com.example.capstone2.Model.Project;
import com.example.capstone2.Model.Student;
import com.example.capstone2.Model.StudentParticipation;
import com.example.capstone2.Repository.EventRepository;
import com.example.capstone2.Repository.ProjectRepository;
import com.example.capstone2.Repository.StudentParticipationRepository;
import com.example.capstone2.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentParticipationService {

    private final StudentParticipationRepository studentParticipationRepository;
    private final EventRepository eventRepository;
    private final StudentRepository studentRepository;
    private final ProjectRepository projectRepository;

    public List<StudentParticipation> getAllStudentParticipation(){
        return studentParticipationRepository.findAll();
    }

    public void updateStudentParticipation(StudentParticipation updatedStudentParticipation, Integer id){
        StudentParticipation studentParticipation = studentParticipationRepository.findStudentParticipationByParticipationId(id);
        if(studentParticipation == null)
            throw new ApiException("Student participation with id " + id + " not found");

        Student student = studentRepository.findStudentByStudentId(updatedStudentParticipation.getStudentId());
        if(student == null)
            throw new ApiException("Student with id " + updatedStudentParticipation.getStudentId() + " not found");

        Project project = projectRepository.findProjectByProjectId(updatedStudentParticipation.getProjectId());
        if (project == null)
            throw new ApiException("Project with id " + updatedStudentParticipation.getProjectId() + " not found");

        studentParticipation.setProjectId(updatedStudentParticipation.getProjectId());
        studentParticipation.setEventId(updatedStudentParticipation.getEventId());

        studentParticipationRepository.save(studentParticipation);
    }

    public void deleteStudentParticipation(Integer id){
        StudentParticipation studentParticipation = studentParticipationRepository.findStudentParticipationByParticipationId(id);
        if(studentParticipation == null)
            throw new ApiException("Student participation with id " + id + " not found");

        studentParticipationRepository.delete(studentParticipation);
    }

    // student can participate in an event
    public void studentParticipateInEvent(StudentParticipation studentParticipation){
        Student student = studentRepository.findStudentByStudentId(studentParticipation.getStudentId());
        if(student == null)
            throw new ApiException("Student with id " + studentParticipation.getStudentId() + " not found");

        Project project = projectRepository.findProjectByProjectId(studentParticipation.getProjectId());
        if (project == null)
            throw new ApiException("Project with id " + studentParticipation.getProjectId() + " not found");

        Event event = eventRepository.findEventByEventId(studentParticipation.getEventId());
        if (event == null)
            throw new ApiException("Event with id " + studentParticipation.getEventId() + " not found");

        if(!project.getStudentId().equals(student.getStudentId()))
            throw new ApiException("Student with id " + student.getStudentId() + " is not the owner of project with id " + project.getProjectId());

        if(studentParticipationRepository.existsStudentParticipationByStudentIdAndEventId(studentParticipation.getStudentId(), studentParticipation.getEventId()))
            throw new ApiException("Student with id " + studentParticipation.getStudentId() + " already participated in the event");

        if(event.getMaxProjectsAllowed() == 0)
            throw new ApiException("Event with id " + event.getEventId() + " is full");

        event.setMaxProjectsAllowed(event.getMaxProjectsAllowed() - 1);
        eventRepository.save(event);
        studentParticipationRepository.save(studentParticipation);
    }

    // get all student participants for an event
    public List<StudentParticipation> getEventParticipants(Integer eventId){
        Event event = eventRepository.findEventByEventId(eventId);
        if (event == null)
            throw new ApiException("Event with id " + eventId + " not found");

        return studentParticipationRepository.findStudentParticipationByEventId(eventId);
    }
}