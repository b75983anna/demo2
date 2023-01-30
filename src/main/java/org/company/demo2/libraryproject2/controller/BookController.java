package org.company.demo2.libraryproject2.controller;

import org.company.demo2.libraryproject2.model.Book;
import org.company.demo2.libraryproject2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public String findAllBooks(final ModelMap model) {
        final List<Book> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "books-list";
    }

    @GetMapping("/add-book")
    public String add(final ModelMap model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    @PostMapping("/save")
    public String save(final ModelMap model, @ModelAttribute final Book book, final BindingResult errors) {
        bookService.save(book);
        return "redirect:books";
    }

    @GetMapping("/delete")
    public String delete(final ModelMap model, @RequestParam final Long id) {
        final Optional<Book> bookRecord = bookService.editBook(id);
        model.addAttribute("book", bookRecord.isPresent() ? bookRecord.get() : new Book());
        model.addAttribute("id", id);
        return "delete";
    }

    @PostMapping("/delete")
    public String save(final ModelMap model, @RequestParam final Long id) {
        bookService.delete(id);
        return "redirect:books";
    }
}