package me.dio.bookshelf.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.dio.bookshelf.dto.BookDto;
import me.dio.bookshelf.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
@Tag(name = "Books Controller", description = "RESTful API for managing books.")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    @Operation(summary = "Get all books", description = "Retrieve a list of all registered books")
    public ResponseEntity<List<BookDto>> findAll() {
        List<BookDto> listOfBooks = bookService.findAll();
        return ResponseEntity.ok().body(listOfBooks);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Get a book by ID", description = "Retrieve a specific book based on its ID")
    public ResponseEntity<BookDto> findById(@PathVariable Long id) {
        BookDto book = bookService.findById(id);
        return ResponseEntity.ok().body(book);
    }

    @PostMapping
    @Operation(summary = "Create a new book", description = "Create a new book and return the created book's data")
    public ResponseEntity<BookDto> create(@RequestBody BookDto bookToCreate) {
        var bookCreated = bookService.create(bookToCreate);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(bookCreated.getId()).toUri();
        return ResponseEntity.created(location).body(bookCreated);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Update a book", description = "Update the data of an existing book based on its ID")
    public ResponseEntity<BookDto> update(@PathVariable Long id, @RequestBody BookDto bookToUpdate) {
        var bookUpdated = bookService.update(id, bookToUpdate);
        return ResponseEntity.ok(bookUpdated);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a book", description = "Delete an existing book based on its ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
