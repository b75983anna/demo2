package org.company.demo2.libraryproject2.service;

import org.company.demo2.libraryproject2.model.Book;
import org.company.demo2.libraryproject2.repository.BookRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    Book book;

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService bookService;

    @BeforeEach
    public void setUp() {
        book = new Book(1L, 20, "It ends with us", "Colleen Hoover", Date.valueOf("2020-12-12"), true);
    }

    @Test
    public void findAllBooks_TestSuccess() {
        Book book2 = new Book(1L, 20, "It ends with us", "Colleen Hoover", Date.valueOf("2020-12-12"), true);
        given(bookRepository.findAll()).willReturn(Arrays.asList(book, book2));
        List<Book> books = bookService.findAllBooks();
        assertThat(books).hasSize(2);
    }

    @Test
    public void findAllBooks_TestEmptyList() {
        given(bookRepository.findAll()).willReturn(Collections.emptyList());
        List<Book> books = bookService.findAllBooks();
        assertThat(books).isEmpty();
    }

    @Test
    public void saveBook_Test() {
        given(bookRepository.save(book)).willReturn(book);
        Book savedBook = bookService.save(book);

        assertThat(savedBook).isNotNull();
        then(bookRepository).should().save(savedBook);
    }

    @Test
    public void deleteBook_Test() {
        Book book3 = new Book();
        book3.setId(1L);
        book3.setBookCode(55);
        book3.setTitle("Title");
        book3.setAuthor("Author");
        book3.setAddedOn(Date.valueOf("2020-12-12"));
        book3.setRead(true);

        bookService.delete(1L);
        assertTrue(bookService.findAllBooks().isEmpty());
    }
}