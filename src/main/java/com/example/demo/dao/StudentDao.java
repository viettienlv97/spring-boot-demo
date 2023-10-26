package com.example.demo.dao;

import com.example.demo.model.ApiResponse;
import com.example.demo.model.student.Student;
import com.example.demo.model.student.StudentResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentDao {
    StudentResponse insertStudent(UUID id, Student student);

    default StudentResponse insertStudent(Student student) {
        UUID id = UUID.randomUUID();
        return insertStudent(id, student);
    }

    ApiResponse<List<Student>> selectAllStudent();

    Optional<Student> selectStudentById(UUID id);

    int updateStudentById(UUID id, Student student);

    int deleteStudentById(UUID id);
}
