package com.example.springSecImp.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private static final List<Student> STUDENTS = Arrays.asList(
        new Student(1, "Rahul Mhatre "),
        new Student(2,"Shivkeshva"),
        new Student(3, "Ajit Singh"),
        new Student(4,"Shivam Mishra")
    );

//    @GetMapping
//    public List<Student> getAllStudents(){
//        return STUDENTS;
//    }
    @GetMapping(path = "{studentId}")
    public Student getStudentById(@PathVariable("studentId") Integer studentId){
        return STUDENTS.stream().
                filter(student -> studentId.equals(student.getId())).
                findFirst().
                orElseThrow(()-> new IllegalStateException("Student not Found with id: " + studentId));
    }
}
