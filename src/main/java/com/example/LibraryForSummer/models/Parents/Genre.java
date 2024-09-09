package com.example.LibraryForSummer.models.Parents;

import com.example.LibraryForSummer.models.Parent_Child.Book;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Genre")
public class Genre implements Serializable {
    @Id
    @Column(name = "genre_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int genreId;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private Book book;

    @Column(name = "genre")
    private String genre;

    public Genre() {}

    public Genre(String genre) {
        this.genre = genre;
    }

    public Genre(String genre, Object o) {
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
