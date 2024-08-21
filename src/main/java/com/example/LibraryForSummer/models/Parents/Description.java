package com.example.LibraryForSummer.models.Parents;

import com.example.LibraryForSummer.models.Parent_Child.Book;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Description")
public class Description implements Serializable {
    @Id
    @Column(name = "description_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int descriptionId;

    @OneToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private Book book;

    @Column(name = "description")
    private String description;

    public Description() {}

    public Description(String description) {
        this.description = description;
    }

    public int getDescriptionId() {
        return descriptionId;
    }

    public void setDescriptionId(int descriptionId) {
        this.descriptionId = descriptionId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
