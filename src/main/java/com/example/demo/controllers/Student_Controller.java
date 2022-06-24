package com.example.demo.controllers;

import com.example.demo.domain.Student;
import com.example.demo.services.Student_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(path = "/all")
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> student = student_service.getStudents();
        return new ResponseEntity<>(student, HttpStatus.OK);
        //return ResponseEntity.status(HttpStatus.OK).body(student_service.getStudents()); SEGUNDA OPCIÓN EN UNA LÍNEA
    }

    @GetMapping(path = "/one/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable("studentId") Long studentId) {
        return ResponseEntity.status(HttpStatus.OK).body(student_service.getById(studentId));
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Student> registerNewStudent(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.CREATED).body(student_service.addNewStudent(student));
    }

    @DeleteMapping(path = "/delete/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable("studentId") Long studentId) {
        student_service.deleteStudent(studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student){
        student_service.updateStudent(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
