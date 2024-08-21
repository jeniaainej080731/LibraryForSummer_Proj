package com.example.LibraryForSummer.repositories.forParents;

import com.example.LibraryForSummer.models.Parents.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface GenresRepository extends JpaRepository<Genre, Integer> {
    Optional<Genre> findByGenre(String genre);
    List<Genre> findByBookId(int bookId);
}
