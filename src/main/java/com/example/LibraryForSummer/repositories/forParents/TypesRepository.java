package com.example.LibraryForSummer.repositories.forParents;

import com.example.LibraryForSummer.models.Parents.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TypesRepository extends JpaRepository<Type, Integer> {
    Optional<Type> findByType(String type);
    List<Type> findByBookId(int bookId);
}
