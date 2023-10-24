package me.dio.bookshelf.services.impl;

import me.dio.bookshelf.dto.GenreDto;
import me.dio.bookshelf.entities.Genre;
import me.dio.bookshelf.repositories.GenreRepository;
import me.dio.bookshelf.services.GenreService;
import me.dio.bookshelf.services.exceptions.DatabaseException;
import me.dio.bookshelf.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Transactional(readOnly = true)
    @Override
    public List<GenreDto> findAll() {
        return genreRepository.findAll().stream()
                .map(GenreDto::new)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public GenreDto findById(Long id) {
        Optional<Genre> obj = genreRepository.findById(id);
        Genre entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new GenreDto(entity);
    }

    @Transactional()
    @Override
    public GenreDto create(GenreDto dto) {
        Genre entity = new Genre();
        entity.setName(dto.getName());
        entity = genreRepository.save(entity);
        return new GenreDto(entity);
    }

    @Transactional()
    @Override
    public GenreDto update(Long id, GenreDto dto) {
        Optional<Genre> obj = genreRepository.findById(id);
        Genre entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id not found " + id));
        entity.setName(dto.getName());
        entity = genreRepository.save(entity);
        return new GenreDto(entity);
    }

    @Override
    public void delete(Long id) {
        try {
            genreRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }
}
