package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Student {
    private final UUID id;
    private final String name;
    private final int gender;
    private final String email;

    private static int count;

    public Student(@JsonProperty("id") UUID id,
                   @JsonProperty("name") String name,
                   @JsonProperty("gender") int gender,
                   @JsonProperty("email") String email) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.email = email;
        count ++;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public static int getCount() {
        return count;
    }
}
