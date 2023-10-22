package me.dio.bookshelf.repositories;

import me.dio.bookshelf.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
