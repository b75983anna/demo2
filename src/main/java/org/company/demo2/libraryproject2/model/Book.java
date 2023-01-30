package org.company.demo2.libraryproject2.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq")
    @Column(name = "id")
    private long id;
    @Column(name = "code")
    private Integer bookCode;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "added")
    private Date addedOn;
    @Column(name = "read")
    private boolean isRead;

}
