package org.company.demo2.libraryproject2;

import org.company.demo2.libraryproject2.controller.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ApplicationTest {
    @Autowired
    BookController bookController;
    @Test
    void contextLoads() {  assertThat(bookController).isNotNull();}


}