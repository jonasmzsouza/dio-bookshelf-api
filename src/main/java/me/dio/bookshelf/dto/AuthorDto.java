package me.dio.bookshelf.dto;

import me.dio.bookshelf.entities.Author;

public class AuthorDto {

    private Long id;
    private String name;
    private String photoUrl;
    private String biography;

    public AuthorDto() {
    }

    public AuthorDto(Long id, String name, String photoUrl, String biography) {
        this.id = id;
        this.name = name;
        this.photoUrl = photoUrl;
        this.biography = biography;
    }

    public AuthorDto(Author entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.photoUrl = entity.getPhotoUrl();
        this.biography = entity.getBiography();
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
