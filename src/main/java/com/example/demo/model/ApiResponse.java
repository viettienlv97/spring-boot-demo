package com.example.demo.model;

public class ApiResponse<T> {
    private int code;
    private T data;

    public ApiResponse(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }
}
