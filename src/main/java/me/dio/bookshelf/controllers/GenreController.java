package me.dio.bookshelf.controllers;

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
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreDto>> findAll() {
        List<GenreDto> listOfGenres = genreService.findAll();
        return ResponseEntity.ok().body(listOfGenres);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GenreDto> findById(@PathVariable Long id) {
        GenreDto genre = genreService.findById(id);
        return ResponseEntity.ok().body(genre);
    }

    @PostMapping
    public ResponseEntity<GenreDto> create(@RequestBody GenreDto genreToCreate) {
        var genreCreated = genreService.create(genreToCreate);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(genreCreated.getId()).toUri();
        return ResponseEntity.created(location).body(genreCreated);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GenreDto> update(@PathVariable Long id, @RequestBody GenreDto genreToUpdate) {
        var genreUpdated = genreService.update(id, genreToUpdate);
        return ResponseEntity.ok().body(genreUpdated);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        genreService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
