package me.dio.bookshelf.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.dio.bookshelf.dto.AuthorDto;
import me.dio.bookshelf.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/authors")
@Tag(name = "Authors Controller", description = "RESTful API for managing authors.")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    @Operation(summary = "Get all authors", description = "Retrieve a list of all registered authors")
    public ResponseEntity<List<AuthorDto>> findAll() {
        List<AuthorDto> listOfAuthor = authorService.findAll();
        return ResponseEntity.ok().body(listOfAuthor);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Get a author by ID", description = "Retrieve a specific author based on its ID")
    public ResponseEntity<AuthorDto> findById(@PathVariable Long id) {
        AuthorDto author = authorService.findById(id);
        return ResponseEntity.ok().body(author);
    }

    @PostMapping
    @Operation(summary = "Create a new author", description = "Create a new author and return the created author's data")
    public ResponseEntity<AuthorDto> create(@RequestBody AuthorDto authorToCreate) {
        var authorCreated = authorService.create(authorToCreate);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(authorCreated.getId()).toUri();
        return ResponseEntity.created(location).body(authorCreated);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Update a author", description = "Update the data of an existing author based on its ID")
    public ResponseEntity<AuthorDto> update(@PathVariable Long id, @RequestBody AuthorDto authorToUpdate) {
        var authorUpdated = authorService.update(id, authorToUpdate);
        return ResponseEntity.ok().body(authorUpdated);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a author", description = "Delete an existing author based on its ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
