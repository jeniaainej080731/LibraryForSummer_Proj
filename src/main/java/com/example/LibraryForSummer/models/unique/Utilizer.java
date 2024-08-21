package com.example.LibraryForSummer.models.unique;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Utilizer")
public class Utilizer {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "name")
    @NotEmpty(message = "Имя не должно быть пустым")
    private String name;

    @Column(name = "email")
    @Email
    @NotEmpty(message = "Поле, содержащее почту, не должно быть пустым")
    private String email;

    @Column(name = "password")
    @NotEmpty(message = "Пароль не должен быть пустым")
    @Size(min = 6, message = "Пароль должен содержать не меньше 6 символов")
    private String password;

    public Utilizer() {}

    public Utilizer(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public @NotEmpty(message = "Имя не должно быть пустым") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Имя не должно быть пустым") String name) {
        this.name = name;
    }

    public @Email @NotEmpty(message = "Поле, содержащее почту, не должно быть пустым") String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotEmpty(message = "Поле, содержащее почту, не должно быть пустым") String email) {
        this.email = email;
    }

    public @NotEmpty(message = "Пароль не должен быть пустым") @Size(min = 6, message = "Пароль должен содержать не меньше 6 символов") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "Пароль не должен быть пустым") @Size(min = 6, message = "Пароль должен содержать не меньше 6 символов") String password) {
        this.password = password;
    }
}
