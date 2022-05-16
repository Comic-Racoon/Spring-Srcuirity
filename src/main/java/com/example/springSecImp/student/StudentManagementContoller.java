package com.example.springSecImp.student;


import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/management/api/v1/students")
public class StudentManagementContoller {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "Adam Smith"),
            new Student(2,"Stuart Little"),
            new Student(3, "Monkey D. Liffy"),
            new Student(4,"Yu Gi Oh")
    );

    @GetMapping
    public List<Student> getAllStudents(){
        return STUDENTS;
    }

    @PostMapping()
    public void registerNewStudent(@RequestBody Student student){
        System.out.println(student);
        STUDENTS.add(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Integer studentId){
        System.out.println(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student){
        System.out.println(String.format("%s %s ", studentId, student));
    }



}
