package me.dio.bookshelf.dto;

import me.dio.bookshelf.entities.Author;
import me.dio.bookshelf.entities.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BookDto {

    private Long id;
    private String name;
    private String sinopse;
    private Integer publicationYear;
    private String publisher;
    private String coverUrl;
    private Integer numberOfPages;
    private GenreDto genre;
    private List<AuthorDto> authors = new ArrayList<>();

    public BookDto() {
    }

    public BookDto(
            Long id,
            String name,
            String sinopse,
            Integer publicationYear,
            String publisher,
            String coverUrl,
            Integer numberOfPages,
            GenreDto genre
    ) {
        this.id = id;
        this.name = name;
        this.sinopse = sinopse;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.coverUrl = coverUrl;
        this.numberOfPages = numberOfPages;
        this.genre = genre;
    }

    public BookDto(Book entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.sinopse = entity.getSinopse();
        this.publicationYear = entity.getPublicationYear();
        this.publisher = entity.getPublisher();
        this.coverUrl = entity.getCoverUrl();
        this.numberOfPages = entity.getNumberOfPages();
        this.genre = new GenreDto(entity.getGenre());
    }

    public BookDto(Book entity, Set<Author> authors) {
        this(entity);
        authors.forEach(author -> this.authors.add(new AuthorDto(author)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public GenreDto getGenre() {
        return genre;
    }

    public void setGenre(GenreDto genre) {
        this.genre = genre;
    }

    public List<AuthorDto> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorDto> authors) {
        this.authors = authors;
    }
}
