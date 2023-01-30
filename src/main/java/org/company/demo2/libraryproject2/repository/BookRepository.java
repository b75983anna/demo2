package org.company.demo2.libraryproject2.repository;

import org.company.demo2.libraryproject2.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
