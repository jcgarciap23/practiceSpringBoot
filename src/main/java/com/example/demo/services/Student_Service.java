package com.example.demo.services;

import com.example.demo.domain.Student;
import com.example.demo.student.Student_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Student_Service {

    private final Student_Repository student_repository;

    @Autowired
    public Student_Service(Student_Repository student_repository) {
        this.student_repository = student_repository;
    }

    public List<Student> getStudents(){
        return (List<Student>) student_repository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = student_repository.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()){
            throw new IllegalStateException("Email existe");
        }
        student_repository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean existStudent = student_repository.existsById(studentId);
        if (!existStudent){
            throw new IllegalStateException("Estudiante con id:"+ studentId +" no existe");
        }
        student_repository.deleteById(studentId);
    }
}
