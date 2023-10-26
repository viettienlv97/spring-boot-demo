package com.example.demo.model.student;

public class StudentResponse {
    private Student student;
    private int totalCount;

    public StudentResponse(Student student, int totalCount) {
        this.student = student;
        this.totalCount = totalCount;
    }

    public StudentResponse(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
