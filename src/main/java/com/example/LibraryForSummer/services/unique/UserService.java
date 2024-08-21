package com.example.LibraryForSummer.services.unique;

import com.example.LibraryForSummer.models.unique.Utilizer;
import com.example.LibraryForSummer.repositories.unique.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<Utilizer> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(Utilizer user) {
        userRepository.save(user);
    }

    public boolean checkPassword(Utilizer user, String password) {
        return user.getPassword().equals(password);
    }
}
