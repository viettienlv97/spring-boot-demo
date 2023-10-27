package com.example.demo.model.student;

import java.util.List;

public class ListStudent {
    private List<Student> students;

    private int totalCount;

    public ListStudent(List<Student> students, int totalCount) {
        this.students = students;
        this.totalCount = totalCount;
    }

    public List<Student> getStudents() {
        return students;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
