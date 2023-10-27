package com.example.demo.dao;

import com.example.demo.model.ApiResponse;
import com.example.demo.model.student.ListStudent;
import com.example.demo.model.student.Student;
import com.example.demo.model.student.StudentResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentDao {
    ApiResponse<UUID> insertStudent(UUID id, Student student);

    default ApiResponse<UUID> insertStudent(Student student) {
        UUID id = UUID.randomUUID();
        return insertStudent(id, student);
    }

    ApiResponse<ListStudent> selectAllStudent();

    ApiResponse<Optional<Student>> selectStudentById(UUID id);

    ApiResponse<UUID> updateStudentById(UUID id, Student student);

    ApiResponse<UUID> deleteStudentById(UUID id);
}
