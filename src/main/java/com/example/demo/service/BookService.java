package com.example.demo.service;

import com.example.demo.response.Code;
import com.example.demo.response.Status;
import com.example.demo.response.ApiResponse;
import com.example.demo.model.book.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public UUID addNewBook(Book book) {
        book.setId(UUID.randomUUID());
        book.setCategoryId(null);
        book.setCategoryName(null);
        bookRepository.save(book);
        return book.getId();
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(UUID id) {
        return bookRepository.findById(id);
    }

    public Optional<Book> updateBook(UUID id, Book bookDetails) {
        Optional<Book> objBook = bookRepository.findById(id);
        if (objBook.isPresent()) {
            Book book = objBook.get();
            book.setName(bookDetails.getName());
            book.setDescription(bookDetails.getDescription());
            bookRepository.save(book);
            return bookRepository.findById(id);
        }
        return Optional.empty();
    }

    public String deleteAllBooks() {
        bookRepository.deleteAll();
        return "All books have been deleted";
    }

    public ApiResponse<UUID> deleteBookById(UUID id) {
        if (bookRepository.findById(id).isEmpty())
            return new ApiResponse<>(Code.ID_NOT_FOUND, Status.FAIL, id);
        bookRepository.deleteById(id);
        return new ApiResponse<>(Code.OK, Status.OK, id);
    }
}
