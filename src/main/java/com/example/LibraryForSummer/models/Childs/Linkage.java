package com.example.LibraryForSummer.models.Childs;

import com.example.LibraryForSummer.models.Parent_Child.Book;
import com.example.LibraryForSummer.models.Person;
import jakarta.persistence.*;

@Entity
@Table(name = "Linkage")
public class Linkage {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private Book book;

    public Linkage() {}

    public Linkage(Person person, Book book) {
        this.person = person;
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
