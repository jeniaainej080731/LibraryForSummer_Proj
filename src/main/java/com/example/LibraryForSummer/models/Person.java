package com.example.LibraryForSummer.models;

import com.example.LibraryForSummer.models.Childs.History;
import com.example.LibraryForSummer.models.Childs.Linkage;
import com.example.LibraryForSummer.models.Parents.Comments;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Поле, содержащее имя, не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя не должно быть меньше 2 символов")
    @Column(name = "name")
    private String name;

    @Email
    @NotEmpty(message = "Поле, содержащее почту, не должно быть пустым")
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "person")
    private List<History> history;

    @OneToMany(mappedBy = "person")
    private List<Linkage> linkages;

    @OneToMany(mappedBy = "user")
    private List<Comments> comments;

    public Person() {}

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotEmpty(message = "Поле, содержащее имя, не должно быть пустым") @Size(min = 2, max = 100, message = "Имя не должно быть меньше 2 символов") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Поле, содержащее имя, не должно быть пустым") @Size(min = 2, max = 100, message = "Имя не должно быть меньше 2 символов") String name) {
        this.name = name;
    }

    public @Email @NotEmpty(message = "Поле, содержащее почту, не должно быть пустым") String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotEmpty(message = "Поле, содержащее почту, не должно быть пустым") String email) {
        this.email = email;
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

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }
}
