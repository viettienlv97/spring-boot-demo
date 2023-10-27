package com.example.demo.service;

import com.example.demo.ResponseCode;
import com.example.demo.ResponseStatus;
import com.example.demo.dao.StudentDao;
import com.example.demo.model.ApiResponse;
import com.example.demo.model.student.ListStudent;
import com.example.demo.model.student.Student;
import com.example.demo.model.student.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
    private final StudentDao studentDao;

    @Autowired
    public StudentService(@Qualifier("fakeStudentDao") StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public ApiResponse<UUID> addStudent(Student student) {
        return studentDao.insertStudent(student);
    }

    public ApiResponse<ListStudent> getAllStudent() {
        return studentDao.selectAllStudent();
    }

    public ApiResponse<Optional<Student>> getStudentById(UUID id) {
        return studentDao.selectStudentById(id);
    }

    public ApiResponse<UUID> updateStudentById(UUID id, Student student) {
        return studentDao.updateStudentById(id, student);
    }

    public ApiResponse<UUID> deleteStudentById(UUID id) {
        return studentDao.deleteStudentById(id);
    }

    public <T> ApiResponse<T> invalidInput() {
        return new ApiResponse<>(ResponseCode.INVALID_INPUT, ResponseStatus.FAIL);
    }
}
