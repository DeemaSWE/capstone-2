package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Student;
import com.example.capstone2.Repository.InvestorRepository;
import com.example.capstone2.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final InvestorRepository investorRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public void addStudent(Student student){
        String email = student.getEmail();
        if(studentRepository.existsStudentByEmail(email))
            throw new ApiException("Student with email " + email + " already exists");

        studentRepository.save(student);
    }

    public void addListStudents(List<Student> students){
        studentRepository.saveAll(students);
    }

    public void updateStudent(Student updatedStudent, Integer id){
        Student student = studentRepository.findStudentByStudentId(id);

        if (student == null)
            throw new ApiException("Student with id " + id + " not found");

        String updatedEmail = updatedStudent.getEmail();
        if(studentRepository.existsStudentByEmail(updatedEmail) && !student.getEmail().equals(updatedEmail))
            throw new ApiException("Student with email " + updatedEmail + " already exists");


        student.setEmail(updatedStudent.getEmail());
        student.setPassword(updatedStudent.getPassword());
        student.setName(updatedStudent.getName());
        student.setPhoneNumber(updatedStudent.getPhoneNumber());

        studentRepository.save(student);
    }

    public void deleteStudent(Integer id){
        Student student = studentRepository.findStudentByStudentId(id);

        if (student == null)
            throw new ApiException("Student with id " + id + " not found");

        studentRepository.delete(student);
    }


}
