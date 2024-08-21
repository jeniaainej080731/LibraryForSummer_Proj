package com.example.LibraryForSummer.services.forParents;

import com.example.LibraryForSummer.models.Parents.Photo_url;
import com.example.LibraryForSummer.repositories.forParents.Photos_url_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class Photos_url_Service {
    private final Photos_url_Repository photos_url_Repository;

    @Autowired
    public Photos_url_Service(Photos_url_Repository photos_url_Repository) {
        this.photos_url_Repository = photos_url_Repository;
    }

    public Photo_url findById(int id) {
        return photos_url_Repository.findById(id).get();
    }

    @Transactional
    public void save(Photo_url photo_url) {
        photos_url_Repository.save(photo_url);
    }

    @Transactional
    public void update(int id, Photo_url photo_url) {
        photo_url.setPhoto_url_id(id);
        photos_url_Repository.save(photo_url);
    }

    @Transactional
    public void delete(int id) {
        photos_url_Repository.deleteById(id);
    }

    public Optional<Photo_url> findByPhotoUrl(String photoUrl) {
        return photos_url_Repository.findByPhotoUrl(photoUrl);
    }

    public Photo_url findPhotoUrlByBookId(int bookId) {
        return photos_url_Repository.findPhoto_urlByBookId(bookId);
    }

    public void deleteByBookId(int bookId) {
        Photo_url photo_url = photos_url_Repository.findPhoto_urlByBookId(bookId);
        if (photo_url != null) {
            photos_url_Repository.delete(photo_url);
        }
    }
}
