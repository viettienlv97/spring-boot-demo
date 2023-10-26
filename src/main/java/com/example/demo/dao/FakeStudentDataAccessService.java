package com.example.demo.dao;

import com.example.demo.model.ApiResponse;
import com.example.demo.model.student.Student;
import com.example.demo.model.student.StudentResponse;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeStudentDao")
public class FakeStudentDataAccessService implements StudentDao {
    private static final List<Student> DB = new ArrayList<>();

    @Override
    public ApiResponse<StudentResponse> insertStudent(UUID id, Student student) {
        boolean hasStudentWithSameEmail = DB.stream()
                .anyMatch(std -> std.getEmail().equals(student.getEmail()));

        if (hasStudentWithSameEmail) {
            System.out.println("Occur same email");
            int code = -2;
            return new ApiResponse<>(code, null);
        }

        DB.add(new Student(id, student.getName(), student.getGender(), student.getEmail()));
        int totalCount = DB.size();

        Optional<Student> insertedStudent = DB.stream()
            .filter(std -> std.getId().equals(id))
            .findFirst();

        return new ApiResponse<>(0, new StudentResponse(insertedStudent.orElse(null), totalCount));
    }

    @Override
    public ApiResponse<List<Student>> selectAllStudent() {
        List<Student> students = DB;
        int code = students.isEmpty() ? -1 : 0;

        return new ApiResponse<>(code, students);
    }

    @Override
    public Optional<Student> selectStudentById(UUID id) {
        return DB.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst();
    }

    @Override
    public int updateStudentById(UUID id, Student update) {
        return selectStudentById(id)
                .map(student -> {
                    int index = DB.indexOf(student);
                    if (index >= 0) {
                        DB.set(index,
                                new Student(id,
                                        update.getName(),
                                        update.getGender(),
                                        update.getEmail()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

    @Override
    public int deleteStudentById(UUID id) {
        Optional<Student> student = selectStudentById(id);
        if (student.isEmpty()) {
            return 0;
        }
        DB.remove(student.get());
        return 1;
    }
}
