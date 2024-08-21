package com.example.LibraryForSummer.services.forParents;

import com.example.LibraryForSummer.models.Parents.Genre;
import com.example.LibraryForSummer.repositories.forParents.GenresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GenresService {
    private final GenresRepository genresRepository;

    @Autowired
    public GenresService(GenresRepository genresRepository) {
        this.genresRepository = genresRepository;
    }

    public List<Genre> findAll() {
        return genresRepository.findAll();
    }

    public Genre findById(int id) {
        Optional<Genre> genre = genresRepository.findById(id);
        return genre.orElse(null);
    }

    @Transactional
    public void save(Genre genre) {
        genresRepository.save(genre);
    }

    @Transactional
    public void update(int id, Genre genre) {
        genre.setGenreId(id);
        genresRepository.save(genre);
    }

    @Transactional
    public void delete(int id) {
        genresRepository.deleteById(id);
    }

    public Optional<Genre> findByGenre(String genre) {
        return genresRepository.findByGenre(genre);
    }
}
