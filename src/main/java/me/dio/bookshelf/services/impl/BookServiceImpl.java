package me.dio.bookshelf.services.impl;

import me.dio.bookshelf.dto.BookDto;
import me.dio.bookshelf.dto.GenreDto;
import me.dio.bookshelf.entities.Author;
import me.dio.bookshelf.entities.Book;
import me.dio.bookshelf.repositories.AuthorRepository;
import me.dio.bookshelf.repositories.BookRepository;
import me.dio.bookshelf.repositories.GenreRepository;
import me.dio.bookshelf.services.BookService;
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
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Transactional(readOnly = true)
    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(BookDto::new)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public BookDto findById(Long id) {
        Optional<Book> obj = bookRepository.findById(id);
        Book entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new BookDto(entity);
    }

    @Transactional()
    @Override
    public BookDto create(BookDto dto) {
        Book entity = new Book();
        copyDtoToEntity(dto, entity);
        entity = bookRepository.save(entity);
        return new BookDto(entity);
    }

    @Transactional()
    @Override
    public BookDto update(Long id, BookDto dto) {
        Optional<Book> obj = bookRepository.findById(id);
        Book entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id not found " + id));
        copyDtoToEntity(dto, entity);
        entity = bookRepository.save(entity);
        return new BookDto(entity);
    }

    @Override
    public void delete(Long id) {
        try {
            bookRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(BookDto dto, Book entity) {
        entity.setName(dto.getName());
        entity.setSinopse(dto.getSinopse());
        entity.setPublicationYear(dto.getPublicationYear());
        entity.setPublisher(dto.getPublisher());
        entity.setCoverUrl(dto.getCoverUrl());
        entity.setNumberOfPages(dto.getNumberOfPages());

        GenreDto genreDto = dto.getGenre();
        entity.setGenre(genreRepository.getReferenceById(genreDto.getId()));

        entity.getAuthors().clear();
        dto.getAuthors().forEach(authorDto -> {
            Author author = authorRepository.getReferenceById(authorDto.getId());
            entity.getAuthors().add(author);
        });
    }
}
