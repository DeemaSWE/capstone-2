package com.example.capstone2.Repository;

import com.example.capstone2.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

    Student findStudentByStudentId(Integer id);
    Boolean existsStudentByEmail(String email);

    List<Student> findStudentsByStudentIdIn(List<Integer> studentIds);
}
