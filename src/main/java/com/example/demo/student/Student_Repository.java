package com.example.demo.student;

import com.example.demo.domain.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Student_Repository extends CrudRepository<Student, Long> {

    Optional<Student> findStudentByEmail(String email);

}
