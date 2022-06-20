package com.example.demo.controllers;

import com.example.demo.domain.Student;
import com.example.demo.services.Student_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path="api/student")
public class Student_Controller {

    private final Student_Service student_service;

    @Autowired
    public Student_Controller(Student_Service student_service) {
        this.student_service = student_service;
    }

    @GetMapping
    public List<Student> getStudents() {
        return student_service.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        student_service.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        student_service.deleteStudent(studentId);
    }

}
