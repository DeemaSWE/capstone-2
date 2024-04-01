package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Project;
import com.example.capstone2.Model.Student;
import com.example.capstone2.Repository.ProjectRepository;
import com.example.capstone2.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final StudentRepository studentRepository;

    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }

    public void addProject(Project project){
        Student student = studentRepository.findStudentByStudentId(project.getStudentId());

        if(student == null)
            throw new ApiException("Student with id " + project.getStudentId() + " not found");

        projectRepository.save(project);
    }

    public void addListProjects(List<Project> projects){
        projectRepository.saveAll(projects);
    }

    public void updateProject(Project updatedProject, Integer id){
        Project project = projectRepository.findProjectByProjectId(id);
        if (project == null)
            throw new ApiException("Project with id " + id + " not found");

        Student student = studentRepository.findStudentByStudentId(updatedProject.getStudentId());
        if(student == null)
            throw new ApiException("Student with id " + project.getStudentId() + " not found");

        project.setTitle(updatedProject.getTitle());
        project.setDescription(updatedProject.getDescription());
        project.setCategory(updatedProject.getCategory());
        project.setCategory(updatedProject.getCategory());
        project.setUniversity(updatedProject.getUniversity());

        projectRepository.save(project);
    }

    public void deleteProject(Integer id){
        Project project = projectRepository.findProjectByProjectId(id);

        if (project == null)
            throw new ApiException("Project with id " + id + " not found");

        projectRepository.delete(project);
    }

    // search for projects based on various criteria such as project title, description, category, university, and city
    public List<Project> searchProjects(String searchTerm){
        return projectRepository.searchProjectByKeyword(searchTerm);
    }

    // get all projects for a student
    public List<Project> getStudentProjects(Integer studentId){
        return projectRepository.findProjectsByStudentId(studentId);
    }

    // filter projects by funding range
    public List<Project> filterProjectsByFunding(Double min, Double max){
        return projectRepository.findProjectsByDesiredFundingBetween(min, max);
    }

    // filter projects by category
    public List<Project> filterProjectsByCategory(String category){
        return projectRepository.findProjectsByCategory(category);
    }

    // filter projects by university
    public List<Project> filterProjectsByUniversity(String university){
        return projectRepository.findProjectsByUniversity(university);
    }



}
