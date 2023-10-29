package com.example.demo.model.book;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    private UUID id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "category")
    Set<CategoriesOfBooks> books;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<CategoriesOfBooks> getBooks() {
        return books;
    }

    public void setBooks(Set<CategoriesOfBooks> books) {
        this.books = books;
    }
}
