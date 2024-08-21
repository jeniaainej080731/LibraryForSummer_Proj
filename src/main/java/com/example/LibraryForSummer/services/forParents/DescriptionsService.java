package com.example.LibraryForSummer.services.forParents;

import com.example.LibraryForSummer.models.Parents.Description;
import com.example.LibraryForSummer.repositories.forParents.DescriptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DescriptionsService {
    private final DescriptionsRepository descriptionsRepository;

    @Autowired
    public DescriptionsService(DescriptionsRepository descriptionsRepository) {
        this.descriptionsRepository = descriptionsRepository;
    }

    public Description findById(int id) {
        return descriptionsRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Description description) {
        descriptionsRepository.save(description);
    }

    @Transactional
    public void update(int id, Description description) {
        description.setDescriptionId(id);
        descriptionsRepository.save(description);
    }

    @Transactional
    public void update(Description description) {
        descriptionsRepository.save(description);
    }

    @Transactional
    public void delete(int id) {
        descriptionsRepository.deleteById(id);
    }

    public Optional<Description> findByDescription(String description) {
        return descriptionsRepository.findByDescription(description);
    }

    public Description findDescriptionByBookId(int bookId) {
        return descriptionsRepository.findDescriptionByBookId(bookId);
    }

    public void deleteByBookId(int bookId) {
        Description description = descriptionsRepository.findDescriptionByBookId(bookId);
        if (description != null) {
            descriptionsRepository.delete(description);
        }
    }
}
