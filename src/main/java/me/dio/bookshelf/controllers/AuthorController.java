package me.dio.bookshelf.controllers;

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
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorDto>> findAll() {
        List<AuthorDto> listOfAuthor = authorService.findAll();
        return ResponseEntity.ok().body(listOfAuthor);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AuthorDto> findById(@PathVariable Long id) {
        AuthorDto author = authorService.findById(id);
        return ResponseEntity.ok().body(author);
    }

    @PostMapping
    public ResponseEntity<AuthorDto> create(@RequestBody AuthorDto authorToCreate) {
        var authorCreated = authorService.create(authorToCreate);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(authorCreated.getId()).toUri();
        return ResponseEntity.created(location).body(authorCreated);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AuthorDto> update(@PathVariable Long id, @RequestBody AuthorDto authorToUpdate) {
        var authorUpdated = authorService.update(id, authorToUpdate);
        return ResponseEntity.ok().body(authorUpdated);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
