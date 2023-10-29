package com.example.demo.model.book;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "books_categories")
public class CategoriesOfBooks {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    Book book;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
