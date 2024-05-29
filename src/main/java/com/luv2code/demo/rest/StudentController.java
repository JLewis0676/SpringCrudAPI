package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private List<Student> theStudents;

    @PostConstruct
    public void loadStudents(){
        this.theStudents = new ArrayList<Student>();
        this.theStudents.add(new Student("Jordan","Lewis"));
        this.theStudents.add(new Student("Vangie","Lewis"));
        this.theStudents.add(new Student("Keyonna","Giles"));
    }
    @GetMapping("/getStudents")
    public List<Student> getStudents(){

        return this.theStudents;
    }
    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable int studentId){
        return this.theStudents.get(studentId);
    }
}
