package com.example.demo.dao;

import com.example.demo.model.Person;
import com.example.demo.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeStudentDao")
public class FakeStudentDataAccessService implements StudentDao {
    private static List<Student> DB = new ArrayList<>();
    @Override
    public int insertStudent(UUID id, Student student) {
        DB.add(new Student(id, student.getName(), student.getGender()));
        return 1;
    }

    @Override
    public List<Student> selectAllStudent() {
        return DB;
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
                        DB.set(index, new Student(id, update.getName(), update.getGender()));
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
