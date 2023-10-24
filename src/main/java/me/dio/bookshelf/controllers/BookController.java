package me.dio.bookshelf.controllers;

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
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDto>> findAll() {
        List<BookDto> listOfBooks = bookService.findAll();
        return ResponseEntity.ok().body(listOfBooks);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookDto> findById(@PathVariable Long id) {
        BookDto book = bookService.findById(id);
        return ResponseEntity.ok().body(book);
    }

    @PostMapping
    public ResponseEntity<BookDto> create(@RequestBody BookDto bookToCreate) {
        var bookCreated = bookService.create(bookToCreate);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(bookCreated.getId()).toUri();
        return ResponseEntity.created(location).body(bookCreated);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BookDto> update(@PathVariable Long id, @RequestBody BookDto bookToUpdate) {
        var bookUpdated = bookService.update(id, bookToUpdate);
        return ResponseEntity.ok(bookUpdated);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
