package com.example.LibraryForSummer.services;

import com.example.LibraryForSummer.models.Parent_Child.Book;
import com.example.LibraryForSummer.models.Parents.Genre;
import com.example.LibraryForSummer.models.Parents.Type;
import com.example.LibraryForSummer.repositories.BooksRepository;
import com.example.LibraryForSummer.repositories.forParents.GenresRepository;
import com.example.LibraryForSummer.repositories.forParents.Photos_url_Repository;
import com.example.LibraryForSummer.repositories.forParents.TypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;
    private final GenresRepository genresRepository;
    private final TypesRepository typesRepository;
    private final Photos_url_Repository photos_url_Repository;

    @Autowired
    public BooksService(BooksRepository booksRepository, GenresRepository genresRepository, TypesRepository typesRepository, Photos_url_Repository photosUrlRepository) {
        this.booksRepository = booksRepository;
        this.genresRepository = genresRepository;
        this.typesRepository = typesRepository;
        photos_url_Repository = photosUrlRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Book findById(int id) {
        Optional<Book> book = booksRepository.findById(id);
        return book.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book book) {
        book.setId(id);
        booksRepository.save(book);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    public List<Book> findByTitle(String title) {
        return booksRepository.findByTitle(title);
    }

    public List<Book> findByAuthor(String author) {
        return booksRepository.findByAuthor(author);
    }

    public List<Book> findByGenre(Genre genre) {
        return booksRepository.findAll().stream()
                .filter(book -> book.getGenres().contains(genre))
                .toList();
    }

    public List<Book> findByType(Type type) {
        return booksRepository.findAll().stream()
                .filter(book -> type.equals(book.getType()))
                .toList();
    }

    @Transactional
    public void assignGenre(int bookId, Genre genre) {
        genre.setBook(booksRepository.findById(bookId).orElse(null));
        genresRepository.save(genre);
    }

    @Transactional
    public void assignType(int bookId, Type type) {
        type.setBook(booksRepository.findById(bookId).orElse(null));
        typesRepository.save(type);
    }

    @Transactional
    public void removeGenre(int bookId, int genreId) {
        Book book = booksRepository.findById(bookId).orElse(null);

        if (book != null) {
            Genre genreToRemove = book.getGenres().stream()
                    .filter(genre -> genre.getGenreId() == genreId)
                    .findFirst()
                    .orElse(null);

            if (genreToRemove != null) {
                genreToRemove.setBook(null);
                genresRepository.save(genreToRemove);

                book.getGenres().remove(genreToRemove);
                booksRepository.save(book);

                System.out.println("Removed genre with ID: " + genreId + " from book with ID: " + bookId);
            }
        }
    }

    @Transactional
    public void clearType(int bookId) {
        Book book = booksRepository.findById(bookId).orElse(null);
        if (book != null) {
            if (book.getType() != null) {
                book.getType().setBook(null);
            }
            book.getType().setBook(null);
            booksRepository.save(book);
        }
    }
}
