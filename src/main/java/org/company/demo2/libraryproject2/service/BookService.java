package org.company.demo2.libraryproject2.service;


import org.company.demo2.libraryproject2.model.Book;
import org.company.demo2.libraryproject2.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }
    public Optional<Book> editBook(final Long id) {
        return bookRepository.findById(id);
    }
    public Book save(final Book book) {
        return bookRepository.save(book);
    }

    public void delete(final Long id) {
        bookRepository.deleteById(id);
    }
}
