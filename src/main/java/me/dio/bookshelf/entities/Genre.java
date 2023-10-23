package me.dio.bookshelf.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "genre")
    private Set<Book> books = new HashSet<>();

    public Genre() {
    }

    public Genre(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
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

    public Set<Book> getBooks() {
        return books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(id, genre.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
