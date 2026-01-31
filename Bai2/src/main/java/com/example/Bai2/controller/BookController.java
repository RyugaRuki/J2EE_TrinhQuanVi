package com.example.Bai2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.Bai2.model.Book;
import com.example.Bai2.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookService.getById(id).orElse(null);
    }

    @PostMapping
    public Book addBook(@RequestBody(required = false) Book book) {
        if (book == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body is missing");
        }
        return bookService.add(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book bookUpdate) {
        return bookService.update(id, bookUpdate);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        boolean removed = bookService.delete(id);
        return removed ? "Xóa thành công sách có ID: " + id : "Không tìm thấy sách";
    }
}
