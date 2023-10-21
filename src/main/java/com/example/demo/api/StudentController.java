package com.example.demo.api;


import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/student")
@RestController
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public void addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudent() {
        return studentService.getAllStudent();
    }

    @GetMapping(path = "{id}")
    public Student getStudentById(@PathVariable("id") UUID id) {
        return studentService.getStudentById(id)
                .orElse(null);
    }

    @PutMapping(path = "{id}")
    public void updateStudentById(@PathVariable("id") UUID id,@RequestBody Student student) {
        studentService.updateStudentById(id, student);
    }

    @DeleteMapping(path = "{id}")
    public void deleteStudentById(@PathVariable("id") UUID id) {
        studentService.deleteStudentById(id);
    }
}
