package com.example.Bai2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Bai2.model.Book;

@Service
public class BookService {

    private final List<Book> books = new ArrayList<>();

    public BookService() {
        books.add(new Book(1, "Nhà giả kim", "Paulo Coelho"));
        books.add(new Book(2, "Dám bị ghét", "Ichiro Kishimi & Fumitake Koga"));
    }

    public List<Book> getAll() {
        return books;
    }

    public Optional<Book> getById(int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst();
    }

    public Book add(Book book) {
        // assign id if missing
        if (book.getId() == 0) {
            int next = books.stream().mapToInt(Book::getId).max().orElse(0) + 1;
            book.setId(next);
        }
        books.add(book);
        return book;
    }

    public Book update(int id, Book update) {
        Optional<Book> opt = getById(id);
        if (opt.isPresent()) {
            Book b = opt.get();
            b.setTitle(update.getTitle());
            b.setAuthor(update.getAuthor());
            return b;
        }
        return null;
    }

    public boolean delete(int id) {
        return books.removeIf(b -> b.getId() == id);
    }
}
