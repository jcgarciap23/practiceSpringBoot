package com.example.demo.services;

import com.example.demo.config.exceptions.Bad_Request_Exception;
import com.example.demo.config.exceptions.Not_Found_Exception;
import com.example.demo.domain.Student;
import com.example.demo.repository.Student_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
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

    public Student getById(Long studentId) {
        return student_repository.findStudentById(studentId).orElseThrow(() -> new Not_Found_Exception(
                "Estudiante con id:"+ studentId +" no existe"
        ));
    }

    public Student addNewStudent(Student student) {

        if(student.getEmail() == null || student.getEmail().length() == 0){
            throw new Bad_Request_Exception("El campo Email está vacío");
        }

        Optional<Student> studentByEmail = student_repository.findStudentByEmail(student.getEmail());

        if (studentByEmail.isPresent()){
            throw new Bad_Request_Exception("Email incorrecto, ya existe un usuario con el email");
        }

        if(student.getFirstName() == null || student.getFirstName().length() == 0){
            throw new Bad_Request_Exception("El campo First Name está vacío");
        }

        if(student.getLastName() == null || student.getLastName().length() == 0){
            throw new Bad_Request_Exception("El campo Last Name está vacío");
        }
        System.out.println(student.getDate());
        if(student.getDate() == null){

            throw new Bad_Request_Exception("El campo Date está vacío");
        }

        return student_repository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean existStudent = student_repository.existsById(studentId);
        if (!existStudent){
            throw new Not_Found_Exception("Estudiante con id:"+ studentId +" no existe");
        }
        student_repository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Student student) {

        if(student.getId() == null){
            throw new Bad_Request_Exception("El campo Id está vacío");
        }

        Student studentCompare = student_repository.findById(student.getId()).orElseThrow( () -> new Not_Found_Exception(
                "Estudiante con id:"+ student.getId() +" no existe"
        ));

        if(
                student.getFirstName() != null
                && student.getFirstName().length() > 0
                && !Objects.equals(studentCompare.getFirstName(), student.getFirstName())
        ){
            studentCompare.setFirstName(student.getFirstName());
        }

        if(
                student.getLastName() != null
                && student.getLastName().length() > 0
                && !Objects.equals(studentCompare.getLastName(), student.getLastName())
        ){
            studentCompare.setLastName(student.getLastName());
        }

        if(
                student.getDate() != null
                && !Objects.equals(studentCompare.getDate(), student.getDate())
        ){
            studentCompare.setDate(student.getDate());
        }

        if(
                student.getEmail() != null
                && student.getEmail().length() > 0
                && !Objects.equals(studentCompare.getEmail(), student.getEmail())
        ){
            Optional<Student> studentByEmail = student_repository.findStudentByEmail(student.getEmail());
            if (studentByEmail.isPresent()){
                throw new IllegalStateException("Email existe");
            }
            studentCompare.setEmail(student.getEmail());
        }
    }

}
