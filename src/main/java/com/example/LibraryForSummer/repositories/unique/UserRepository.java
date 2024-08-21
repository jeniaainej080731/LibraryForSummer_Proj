package com.example.LibraryForSummer.repositories.unique;

import com.example.LibraryForSummer.models.unique.Utilizer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Utilizer, Integer> {
    Optional<Utilizer> findByEmail(String email);

}
