package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        //check the studentId against list size

        if(studentId >= theStudents.size() || studentId < 0){
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        return this.theStudents.get(studentId);
    }

    //Add an exception handler using @ExceptionHandler

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
        //create a StudentErrorResponse
            StudentErrorResponse error = new StudentErrorResponse();
            error.setStatus(HttpStatus.NOT_FOUND.value());
            error.setMessage(exc.getMessage());
            error.setTimeStamp(System.currentTimeMillis());
        //return ResponseEntity

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    //add another exception handler ... to catch any exception (catch all)
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc){
        //create a StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        //return ResponseEntity

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
