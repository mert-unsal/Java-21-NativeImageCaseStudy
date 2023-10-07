package com.munsal.java21demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.munsal.java21demo.domain.jsonView.ViewRole;
import com.munsal.java21demo.domain.model.BookDto;
import com.munsal.java21demo.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(BookController.BASE_PATH)
@RequiredArgsConstructor
@Slf4j
public class BookController {
    public static final String BASE_PATH = "book";
    private static final String TAG = "Book Controllers";
    private final BookService bookService;

    @GetMapping("all")
    @Operation(tags = TAG, summary = "Get All Books")
    @JsonView({ViewRole.ViewRequest.class})
    public List<BookDto> getAllBooks() {
        log.info("User query all books");
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    @Operation(tags = TAG, summary = "Get Book By Id")
    @JsonView({ViewRole.ViewRequest.class})
    public BookDto get(@PathVariable("id") Long bookId) {
        return bookService.getBook(bookId);
    }

    @PostMapping
    @Operation(tags = TAG, summary = "Add a new Book")
    @JsonView({ViewRole.AddRequest.class})
    public void add(@RequestBody @Valid BookDto bookDto) {
        bookService.addBook(bookDto);
    }

    @PutMapping("{id}")
    @Operation(tags = TAG, summary = "Update Book")
    @JsonView({ViewRole.UpsertRequest.class})
    public void update(@PathVariable Long id, @RequestBody @Valid BookDto bookDto) {
        bookService.updateBook(id, bookDto);
    }

    @DeleteMapping("{id}")
    @Operation(tags = TAG, summary = "Delete Book")
    @JsonView({ViewRole.DeleteRequest.class})
    public void delete(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

}
