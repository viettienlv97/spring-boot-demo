package com.example.demo.service;

import com.example.demo.model.book.Category;
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
    private CategoryService categoryService;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public UUID addNewBook(Book book) {
        book.setId(UUID.randomUUID());
        bookRepository.save(book);
        return book.getId();
    }

    // Get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Get book by id
    public Optional<Book> getBookById(UUID id) {
        return bookRepository.findById(id);
    }

    // Update book detail
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

    // Delete all books
    public String deleteAllBooks() {
        bookRepository.deleteAll();
        return "All books have been deleted";
    }

    // Delete book by id
    public ApiResponse<UUID> deleteBookById(UUID id) {
        if (bookRepository.findById(id).isEmpty())
            return new ApiResponse<>(Code.ID_NOT_FOUND, Status.FAIL, id);
        bookRepository.deleteById(id);
        return new ApiResponse<>(Code.OK, Status.OK, id);
    }

    // Add book category
    public ApiResponse<UUID> setBookCategory(UUID bookId, UUID categoryId) {
        Optional<Category> category = categoryService.getCategoryById(categoryId);
        Optional<Book> book = getBookById(bookId);
        if (category.isEmpty() || book.isEmpty()) {
            return null;
        }
        book.get().setCategoryId(categoryId);
        book.get().setCategoryName(category.get().getName());
        bookRepository.save(book.get());
        return new ApiResponse<>(Code.OK, Status.OK, bookId);
    }

    public List<Book> getBookByCategory(UUID categoryId) {
        return bookRepository.findByCategoryId(categoryId);
    }
}
