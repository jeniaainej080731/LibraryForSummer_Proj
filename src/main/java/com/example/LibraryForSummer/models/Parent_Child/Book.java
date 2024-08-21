package com.example.LibraryForSummer.models.Parent_Child;

import com.example.LibraryForSummer.models.Childs.History;
import com.example.LibraryForSummer.models.Childs.Linkage;
import com.example.LibraryForSummer.models.Parents.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    @NotEmpty(message = "Поле, содержащее название, не должно быть пустым")
    @Size(max = 150, message = "Название должно быть не больше 150 символов")
    private String title;

    @Column(name = "author")
    @NotEmpty(message = "Поле автора не должно быть пустым")
    private String author;

    @Column(name = "publisher")
    @NotEmpty(message = "Поле издателя не должно быть пустым")
    private String publisher;

    @Column(name = "creation_year")
    private int creationYear;

    @OneToMany(mappedBy = "book")
    private List<Genre> genres;

    @OneToOne(mappedBy = "book")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Description description;

    @OneToMany(mappedBy = "book")
    private List<Comments> comment;

    @OneToOne(mappedBy = "book")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Type type;

    @OneToOne(mappedBy = "book")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Photo_url photo_url;

    @OneToMany(mappedBy = "book")
    private List<History> history;

    @OneToMany(mappedBy = "book")
    private List<Linkage> linkages;

    @Transient
    private boolean expired;

    @Transient
    private boolean assigned;

    public Book() {}

    public Book(String title, String author, String publisher, int creationYear) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.creationYear = creationYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getCreationYear() {
        return creationYear;
    }

    public void setCreationYear(int creationYear) {
        this.creationYear = creationYear;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
        description.setBook(this);
    }

    public List<Comments> getComments() {
        return comment;
    }

    public void setComments(List<Comments> comments) {
        this.comment = comments;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
        type.setBook(this);
    }

    public Photo_url getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(Photo_url photo_url) {
        this.photo_url = photo_url;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }

    public List<Linkage> getLinkages() {
        return linkages;
    }

    public void setLinkages(List<Linkage> linkages) {
        this.linkages = linkages;
    }

    public List<Comments> getComment() {
        return comment;
    }

    public void setComment(List<Comments> comment) {
        this.comment = comment;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }
}
