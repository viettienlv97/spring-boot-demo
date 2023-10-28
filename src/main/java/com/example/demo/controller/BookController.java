package com.example.demo.controller;

import com.example.demo.response.ApiResponse;
import com.example.demo.model.book.Book;
import com.example.demo.response.Code;
import com.example.demo.response.Status;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @CrossOrigin(origins = "*")
    @PostMapping
    public ApiResponse<UUID> addNewBook(@RequestBody Book book) {
        return new ApiResponse<>(Code.OK, Status.OK, bookService.addNewBook(book));
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public ApiResponse<List<Book>> getAllBooks(){
        return new ApiResponse<>(Code.OK, Status.OK, bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ApiResponse<Optional<Book>> getBookById(@PathVariable UUID id) {
        return new ApiResponse<>(Code.OK, Status.OK, bookService.getBookById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<Optional<Book>> updateBook(@PathVariable UUID id, @RequestBody Book book) {
        return new ApiResponse<>(Code.OK, Status.OK, bookService.updateBook(id, book));
    }

    @DeleteMapping("/delete-all")
    public ApiResponse<String> deleteAllBooks() {
        return new ApiResponse<>(Code.OK, Status.OK, bookService.deleteAllBooks());
    }

    @DeleteMapping("/{id}")
    public ApiResponse<UUID> deleteBookById(@PathVariable UUID id) {
        return bookService.deleteBookById(id);
    }
}
