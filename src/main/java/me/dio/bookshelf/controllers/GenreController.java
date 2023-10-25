package me.dio.bookshelf.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.dio.bookshelf.dto.GenreDto;
import me.dio.bookshelf.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/genres")
@Tag(name = "Genre Controller", description = "RESTful API for managing genres.")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    @Operation(summary = "Get all genres", description = "Retrieve a list of all registered genres")
    public ResponseEntity<List<GenreDto>> findAll() {
        List<GenreDto> listOfGenres = genreService.findAll();
        return ResponseEntity.ok().body(listOfGenres);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Get a genre by ID", description = "Retrieve a specific genre based on its ID")
    public ResponseEntity<GenreDto> findById(@PathVariable Long id) {
        GenreDto genre = genreService.findById(id);
        return ResponseEntity.ok().body(genre);
    }

    @PostMapping
    @Operation(summary = "Create a new genre", description = "Create a new genre and return the created genre's data")
    public ResponseEntity<GenreDto> create(@RequestBody GenreDto genreToCreate) {
        var genreCreated = genreService.create(genreToCreate);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(genreCreated.getId()).toUri();
        return ResponseEntity.created(location).body(genreCreated);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Update a genre", description = "Update the data of an existing genre based on its ID")
    public ResponseEntity<GenreDto> update(@PathVariable Long id, @RequestBody GenreDto genreToUpdate) {
        var genreUpdated = genreService.update(id, genreToUpdate);
        return ResponseEntity.ok().body(genreUpdated);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a genre", description = "Delete an existing genre based on its ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        genreService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
