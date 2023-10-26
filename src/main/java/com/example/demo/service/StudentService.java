package com.example.demo.service;

import com.example.demo.dao.StudentDao;
import com.example.demo.model.ApiResponse;
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

    public StudentResponse addStudent(Student student) {
        return studentDao.insertStudent(student);
    }

    public ApiResponse<List<Student>> getAllStudent() {
        return studentDao.selectAllStudent();
    }

    public Optional<Student> getStudentById(UUID id) {
        return studentDao.selectStudentById(id);
    }

    public int updateStudentById(UUID id, Student student) {
        return studentDao.updateStudentById(id, student);
    }

    public int deleteStudentById(UUID id) {
        return studentDao.deleteStudentById(id);
    }
}
