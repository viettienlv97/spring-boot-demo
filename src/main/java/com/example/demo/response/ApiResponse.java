package com.example.demo.response;

public class ApiResponse<T> {
    private int code;
    private String status;
    private T data;

    public ApiResponse(int code, String status, T data) {
        this.code = code;
        this.status = status;
        this.data = data;
    }

    public ApiResponse(int code, String status){
        this.code = code;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }
}
