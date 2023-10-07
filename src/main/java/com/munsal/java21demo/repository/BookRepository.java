package com.munsal.java21demo.repository;

import com.munsal.java21demo.domain.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    BookEntity save(BookEntity bookEntity);
    Optional<BookEntity> findById(Long bookId);
}
