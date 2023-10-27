package com.example.demo.dao;

import com.example.demo.ResponseCode;
import com.example.demo.ResponseStatus;
import com.example.demo.model.ApiResponse;
import com.example.demo.model.student.ListStudent;
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
    public ApiResponse<UUID> insertStudent(UUID id, Student student) {
        boolean hasStudentWithSameEmail = DB.stream()
                .anyMatch(std -> std.getEmail().equals(student.getEmail()));

        if (hasStudentWithSameEmail) {
            System.out.println("Occur same email");
            return new ApiResponse<>(ResponseCode.DUPLICATE_INPUT, ResponseStatus.FAIL, null);
        }

        DB.add(new Student(id, student.getName(), student.getGender(), student.getEmail()));

        return new ApiResponse<>(ResponseCode.NO_ERROR, ResponseStatus.OK, id);
    }

    @Override
    public ApiResponse<ListStudent> selectAllStudent() {
        return new ApiResponse<>(ResponseCode.NO_ERROR, ResponseStatus.OK, new ListStudent(DB, DB.size()) {
        });
    }

    @Override
    public ApiResponse<Optional<Student>> selectStudentById(UUID id) {
        Optional<Student> student = DB.stream()
                .filter(std -> std.getId().equals(id))
                .findFirst();
        return new ApiResponse<>(ResponseCode.NO_ERROR, ResponseStatus.OK, student);
    }

    private Optional<Student> getStudentById(UUID id) {
        return DB.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst();
    }


    @Override
    public ApiResponse<UUID> updateStudentById(UUID id, Student update) {
        Optional<Student> optionalStudent = getStudentById(id);
        if (optionalStudent.isEmpty()) return new ApiResponse<>(ResponseCode.INVALID_INPUT, ResponseStatus.FAIL, id);
        Student student = optionalStudent.get();
        int index = DB.indexOf(student);
        System.out.println("Index of student: " + index);
        if (index < 0) return new ApiResponse<>(ResponseCode.INVALID_INPUT, ResponseStatus.FAIL, id);
        DB.set(index,
                new Student(id, update.getName(), update.getGender(), update.getEmail()));
        return new ApiResponse<>(ResponseCode.NO_ERROR, ResponseStatus.OK, id);
//                .map(student -> {
//                    int index = DB.indexOf(student);
//                    if (index >= 0) {
//                        DB.set(index,
//                                new Student(id,
//                                        update.getName(),
//                                        update.getGender(),
//                                        update.getEmail()));
//                        return new ApiResponse<>(ResponseCode.NO_ERROR, ResponseStatus.OK, id);
//                    }
//                    return new ApiResponse<>(ResponseCode.INVALID_INPUT, ResponseStatus.FAIL, id);
//                }).orElse(new ApiResponse<>(ResponseCode.INVALID_INPUT, ResponseStatus.FAIL, id));
    }

    @Override
    public ApiResponse<UUID> deleteStudentById(UUID id) {
        Optional<Student> student = getStudentById(id);
        if (student.isEmpty()) {
            return new ApiResponse<>(ResponseCode.ID_NOT_FOUND, ResponseStatus.FAIL, id);
        }
        DB.remove(student.get());
        return new ApiResponse<>(ResponseCode.NO_ERROR, ResponseStatus.OK, id);
    }
}
