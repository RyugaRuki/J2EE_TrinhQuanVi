package com.example.Bai2.controller;

import java.util.ArrayList;
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

@RestController
@RequestMapping("/api/books")
public class BookController {

    private List<Book> books = new ArrayList<>();

    public BookController() {

        books.add(new Book(1, "Nhà giả kim", "Paulo Coelho"));
        books.add(new Book(2, "Dám bị ghét", "Ichiro Kishimi & Fumitake Koga"));
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return books;
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return books.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Book addBook(@RequestBody(required = false) Book book) {
        if (book == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body is missing");
        }
        books.add(book);
        return book;
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book bookUpdate) {
        for (Book b : books) {
            if (b.getId() == id) {
                b.setTitle(bookUpdate.getTitle());
                b.setAuthor(bookUpdate.getAuthor());
                return b;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        boolean removed = books.removeIf(b -> b.getId() == id);
        return removed ? "Xóa thành công sách có ID: " + id : "Không tìm thấy sách";
    }
}
