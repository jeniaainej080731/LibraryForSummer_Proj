package com.example.LibraryForSummer.models.Parents;

import com.example.LibraryForSummer.models.Parent_Child.Book;
import jakarta.persistence.*;

@Entity
@Table(name = "Photos_url")
public class Photo_url {
    @Id
    @Column(name = "photo_url_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int photo_url_id;

    @OneToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private Book book;

    @Column(name = "photo_url")
    private String photoUrl;

    public Photo_url() {}

    public Photo_url(String photo_url) {
        this.photoUrl = photo_url;
    }

    public int getPhoto_url_id() {
        return photo_url_id;
    }

    public void setPhoto_url_id(int photo_url_id) {
        this.photo_url_id = photo_url_id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photo_url) {
        this.photoUrl = photo_url;
    }
}
