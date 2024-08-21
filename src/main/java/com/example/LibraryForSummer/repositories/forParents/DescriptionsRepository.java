package com.example.LibraryForSummer.repositories.forParents;

import com.example.LibraryForSummer.models.Parents.Description;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DescriptionsRepository extends JpaRepository<Description, Integer> {
    Optional<Description> findByDescription(String description);
    Description findDescriptionByBookId(int bookId);
}
