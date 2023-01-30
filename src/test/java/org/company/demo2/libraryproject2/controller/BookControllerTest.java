package org.company.demo2.libraryproject2.controller;

import org.company.demo2.libraryproject2.model.Book;
import org.company.demo2.libraryproject2.repository.BookRepository;
import org.company.demo2.libraryproject2.service.BookService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@WebMvcTest(BookController.class)
@RunWith(SpringRunner.class)
public class BookControllerTest {

    private static final Long ID = 1L;

    @Mock
    Book book;

    @MockBean
    BookService bookService;

    @Autowired
    MockMvc mockMvc;


    @Test
    public void findAllBooks_Test() throws Exception {
        Book book2 = new Book(2L, 21, "Book One", "Author", Date.valueOf("2020-12-12"), true);
        final List<Book> allBooks = Arrays.asList(book, book2);

        when(bookService.findAllBooks()).thenReturn(allBooks);

        mockMvc.perform(get("/books"))

                .andExpect(status().isOk())
                .andExpect(view().name("books-list"))
                .andExpect(model().attribute("books", hasItems(book, book2)))
                .andExpect(model().attribute("books", Matchers.hasSize(2)))
                .andDo(print());
        then(bookService).should().findAllBooks();
    }

    @Test
    public void addBook_Test() throws Exception {
        Book book2 = new Book();
        book2.setId(2L);
        book2.setBookCode(23);
        book2.setTitle("title");
        book2.setAuthor("author");
        book2.setAddedOn(Date.valueOf("2020-12-12"));
        book2.setRead(true);
        when(bookService.editBook(2L)).thenReturn(Optional.of(book2));

        assertThat(book2).extracting("id", "bookCode", "title", "author", "addedOn", "isRead")
                .containsExactly(2L, 23, "title", "author", Date.valueOf("2020-12-12"), true);
        mockMvc.perform(get("/add-book/"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-book"))
                .andExpect(model().attributeExists("book"))
                .andDo(print());
    }

   @Test
    public void saveBook_Test() throws Exception {
        Book book2 = new Book(2L, 33, "BookTwo", "Author Two", Date.valueOf("2020-12-12"), true);
        when(bookService.save(book2)).thenReturn(book2);

        assertThat(book2).extracting("id", "bookCode", "title", "author", "addedOn", "isRead")
                .containsExactly(2L, 33, "BookTwo", "Author Two", Date.valueOf("2020-12-12"), true);
        mockMvc.perform(post("/save/")
                        .flashAttr("book", book))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("books"))
                .andDo(print());
        then(bookService).should().save(book);
    }

    @Test
    public void testDeleteBook() throws Exception {
        willDoNothing().given(bookService).delete(ID);

        mockMvc.perform(post("/delete/")
                        .param("id", ID.toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("books"));
        then(bookService).should().delete(ID);
    }
}