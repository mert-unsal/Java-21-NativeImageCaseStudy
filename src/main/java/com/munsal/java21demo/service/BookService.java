package com.munsal.java21demo.service;

import com.munsal.java21demo.domain.entity.BookEntity;
import com.munsal.java21demo.domain.model.BookDto;
import com.munsal.java21demo.exception.DomainNotFoundException;
import com.munsal.java21demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.munsal.java21demo.exception.ErrorCode.*;
import static com.munsal.java21demo.mapper.BookMapper.BOOK_MAPPER;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream().map(BOOK_MAPPER::toBookDto).toList();
    }

    public BookDto getBook(Long bookId) {
        return bookRepository.findById(bookId).map(BOOK_MAPPER::toBookDto).orElseThrow(() -> {
            throw new DomainNotFoundException(BOOK_COULD_NOT_FOUND, bookId);
        });
    }

    public BookEntity getBookEntity(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> {
            throw new DomainNotFoundException(BOOK_COULD_NOT_FOUND, bookId);
        });
    }

    public void addBook(BookDto bookDto) {
        bookRepository.save(BOOK_MAPPER.toBookEntity(bookDto));
    }

    public void updateBook(Long id, BookDto bookDto) {
        bookRepository.findById(id).ifPresentOrElse((bookEntity)-> {
            BookEntity toCustomerEntity = BOOK_MAPPER.toBookEntity(bookDto);
            toCustomerEntity.setId(bookEntity.getId());
            bookRepository.save(toCustomerEntity);
        }, () -> {
            throw new DomainNotFoundException(BOOK_COULD_NOT_FOUND_TO_BE_UPDATED,id);
        });
    }

    public void deleteBook(Long id) {
        bookRepository.findById(id).ifPresentOrElse((bookEntity) -> bookRepository.deleteById(bookEntity.getId()), () -> {
            throw new DomainNotFoundException(BOOK_COULD_NOT_FOUND_TO_BE_DELETED, id);
        });
    }
}
