package com.example.LibraryForSummer.repositories.forParents;

import com.example.LibraryForSummer.models.Parents.Photo_url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Photos_url_Repository extends JpaRepository<Photo_url, Integer> {
    Optional<Photo_url> findByPhotoUrl(String photoUrl);
    Photo_url findPhoto_urlByBookId(int bookId);
}
