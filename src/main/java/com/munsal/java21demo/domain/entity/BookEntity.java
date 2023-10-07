package com.munsal.java21demo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "book")
@SequenceGenerator(name = "book_seq_gen", allocationSize = 1, sequenceName = "book_seq")
public class BookEntity extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq_gen")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "Publisher")
    private String publisher;

    @Column
    @Temporal(TemporalType.DATE)
    private Date publishDate;

}
