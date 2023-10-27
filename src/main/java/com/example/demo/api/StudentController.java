package com.example.demo.api;


import com.example.demo.model.ApiResponse;
import com.example.demo.model.student.ListStudent;
import com.example.demo.model.student.Student;
import com.example.demo.model.student.StudentResponse;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/student")
@RestController
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping
    public ApiResponse<UUID> addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public ApiResponse<ListStudent> getAllStudent() {
        return studentService.getAllStudent();
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "{id}")
    public ApiResponse<Optional<Student>> getStudentById(@PathVariable("id") String id) {
        try {
            UUID theId = UUID.fromString(id);
            return studentService.getStudentById(theId);
        } catch (Exception e) {
            return studentService.invalidInput();
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "{id}")
    public ApiResponse<UUID> updateStudentById(@PathVariable("id") UUID id,@RequestBody Student student) {
        return studentService.updateStudentById(id, student);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "{id}")
    public ApiResponse<UUID> deleteStudentById(@PathVariable("id") UUID id) {
        return studentService.deleteStudentById(id);
    }
}
