package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiResponse;
import com.example.capstone2.Model.Project;
import com.example.capstone2.Service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/get-all")
    public ResponseEntity getAllProjects(){
        return ResponseEntity.status(200).body(projectService.getAllProjects());
    }

    @PostMapping("/add")
    public ResponseEntity addProject(@RequestBody @Valid Project project, Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        projectService.addProject(project);
        return ResponseEntity.status(200).body(new ApiResponse("Project added successfully"));
    }

    @PostMapping("/add-list")
    public ResponseEntity addProjects(@RequestBody @Valid List<Project> projects, Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        projectService.addListProjects(projects);
        return ResponseEntity.status(200).body(new ApiResponse("Projects added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProject(@PathVariable Integer id, @RequestBody @Valid Project project, Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        projectService.updateProject(project, id);
        return ResponseEntity.status(200).body(new ApiResponse("Project updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProject(@PathVariable Integer id){
        projectService.deleteProject(id);
        return ResponseEntity.status(200).body(new ApiResponse("Project deleted successfully"));
    }

    // search for projects based on various criteria such as project title, description, category, university, and city
    @GetMapping("/search/{searchTerm}")
    public ResponseEntity searchProjects(@PathVariable String searchTerm){
        List<Project> projectsList = projectService.searchProjects(searchTerm);

        if (projectsList.isEmpty())
            return ResponseEntity.status(404).body(new ApiResponse("No projects found"));

        return ResponseEntity.status(200).body(projectsList);
    }

    // get all projects for a student
    @GetMapping("/get-student-projects/{studentId}")
    public ResponseEntity getStudentProjects(@PathVariable Integer studentId){
        List<Project> projectsList = projectService.getStudentProjects(studentId);

        if (projectsList.isEmpty())
            return ResponseEntity.status(404).body(new ApiResponse("No projects found for student with id " + studentId));

        return ResponseEntity.status(200).body(projectsList);
    }

    // filter projects by funding range
    @GetMapping("/filter-by-funding/{min}/{max}")
    public ResponseEntity filterProjectsByFunding(@PathVariable Double min, @PathVariable Double max){
        List<Project> projectsList = projectService.filterProjectsByFunding(min, max);

        if (projectsList.isEmpty())
            return ResponseEntity.status(404).body(new ApiResponse("No projects found within the specified funding range"));

        return ResponseEntity.status(200).body(projectsList);
    }

    // filter projects by category
    @GetMapping("/filter-by-category/{category}")
    public ResponseEntity filterProjectsByCategory(@PathVariable String category){
        List<Project> projectsList = projectService.filterProjectsByCategory(category);

        if (projectsList.isEmpty())
            return ResponseEntity.status(404).body(new ApiResponse("No projects found within the specified category"));

        return ResponseEntity.status(200).body(projectsList);
    }

    // filter projects by university
    @GetMapping("/filter-by-university/{university}")
    public ResponseEntity filterProjectsByUniversity(@PathVariable String university){
        List<Project> projectsList = projectService.filterProjectsByUniversity(university);

        if (projectsList.isEmpty())
            return ResponseEntity.status(404).body(new ApiResponse("No projects found within the specified university"));

        return ResponseEntity.status(200).body(projectsList);
    }


}
