package me.dio.bookshelf.services.impl;

import me.dio.bookshelf.dto.AuthorDto;
import me.dio.bookshelf.entities.Author;
import me.dio.bookshelf.repositories.AuthorRepository;
import me.dio.bookshelf.services.AuthorService;
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
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    @Override
    public List<AuthorDto> findAll() {
        return authorRepository.findAll().stream()
                .map(AuthorDto::new)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public AuthorDto findById(Long id) {
        Optional<Author> obj = authorRepository.findById(id);
        Author entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new AuthorDto(entity);
    }

    @Transactional()
    @Override
    public AuthorDto create(AuthorDto dto) {
        Author entity = new Author();
        copyDtoToEntity(dto, entity);
        entity = authorRepository.save(entity);
        return new AuthorDto(entity);
    }

    @Transactional()
    @Override
    public AuthorDto update(Long id, AuthorDto dto) {
        Optional<Author> obj = authorRepository.findById(id);
        Author entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id not found " + id));
        copyDtoToEntity(dto, entity);
        entity = authorRepository.save(entity);
        return new AuthorDto(entity);
    }

    @Override
    public void delete(Long id) {
        try {
            authorRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(AuthorDto dto, Author entity) {
        entity.setName(dto.getName());
        entity.setPhotoUrl(dto.getPhotoUrl());
        entity.setBiography(dto.getBiography());
    }
}
