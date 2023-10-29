package com.example.demo.model.book;

import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "books")
public class Book {
    @Id
    private UUID id;

    private String name;
    private String description;

    private UUID categoryId;
    private String categoryName;

    @OneToMany(mappedBy = "book")
    Set<CategoriesOfBooks> categories;

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

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<CategoriesOfBooks> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoriesOfBooks> categories) {
        this.categories = categories;
    }
}
