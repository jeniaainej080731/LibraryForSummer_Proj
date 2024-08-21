package com.example.LibraryForSummer.models.Parents;

import com.example.LibraryForSummer.models.Parent_Child.Book;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Type")
public class Type implements Serializable {
    @Id
    @Column(name = "type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int typeId;

    @OneToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private Book book;

    @Column(name = "type")
    private String type;

    public Type() {}

    public Type(String type) {
        this.type = type;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type1 = (Type) o;
        return typeId == type1.typeId && Objects.equals(book, type1.book) && Objects.equals(type, type1.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, book, type);
    }
}
