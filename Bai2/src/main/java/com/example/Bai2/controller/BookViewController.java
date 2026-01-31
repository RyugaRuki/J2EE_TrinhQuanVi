package com.example.Bai2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Bai2.model.Book;
import com.example.Bai2.service.BookService;

@Controller
public class BookViewController {

    private final BookService bookService;

    public BookViewController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping({"/books", "/", "/books/home"})
    public String list(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "books";
    }

    @GetMapping("/books/new")
    public String createForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("action", "/books");
        model.addAttribute("method", "post");
        return "book-form";
    }

    @PostMapping("/books")
    public String create(@ModelAttribute Book book) {
        bookService.add(book);
        return "redirect:/books";
    }

    @GetMapping("/books/{id}/edit")
    public String editForm(@PathVariable int id, Model model) {
        Book b = bookService.getById(id).orElse(new Book());
        model.addAttribute("book", b);
        model.addAttribute("action", "/books/" + id + "/update");
        model.addAttribute("method", "post");
        return "book-form";
    }

    @PostMapping("/books/{id}/update")
    public String update(@PathVariable int id, @ModelAttribute Book book) {
        bookService.update(id, book);
        return "redirect:/books";
    }

    @PostMapping("/books/{id}/delete")
    public String delete(@PathVariable int id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}
