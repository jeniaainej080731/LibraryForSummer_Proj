package com.example.LibraryForSummer.services.forChilds;

import com.example.LibraryForSummer.models.Parent_Child.Book;
import com.example.LibraryForSummer.models.Person;
import com.example.LibraryForSummer.repositories.BooksRepository;
import com.example.LibraryForSummer.repositories.PeopleRepository;
import com.example.LibraryForSummer.repositories.forChilds.LinkageRepository;
import com.example.LibraryForSummer.models.Childs.Linkage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class LinkageService {
    private final LinkageRepository linkageRepository;
    private final PeopleRepository peopleRepository;
    private final BooksRepository booksRepository;

    @Autowired
    public LinkageService(LinkageRepository linkageRepository, PeopleRepository peopleRepository, BooksRepository booksRepository) {
        this.linkageRepository = linkageRepository;
        this.peopleRepository = peopleRepository;
        this.booksRepository = booksRepository;
    }

    public List<Linkage> findAll() {
        return linkageRepository.findAll();
    }

    public Linkage findById(int id) {
        Optional<Linkage> linkage = linkageRepository.findById(id);
        return linkage.orElse(null);
    }

    @Transactional
    public void save(Linkage linkage) {
        linkageRepository.save(linkage);
    }

    @Transactional
    public void update(int id, Linkage linkage) {
        linkage.setId(id);
        linkageRepository.save(linkage);
    }

    @Transactional
    public void delete(int id) {
        linkageRepository.deleteById(id);
    }

    @Transactional
    public void assignBook(int personId, int bookId) {
        Person person = peopleRepository.findById(personId).orElse(null);
        Book book = booksRepository.findById(bookId).orElse(null);

        if (person != null && book != null) {
            Linkage linkage = new Linkage(person, book);
            linkageRepository.save(linkage);
        } else {
            throw new IllegalArgumentException("Person or Book not found");
        }
    }

    @Transactional
    public void removeBook(int personId, int bookId) {
        Linkage linkage = linkageRepository.findAll().stream()
                .filter(l -> l.getPerson().getId() == personId && l.getBook().getId() == bookId)
                .findFirst()
                .orElse(null);

        if (linkage != null) {
            linkageRepository.delete(linkage);
        } else {
            throw new IllegalArgumentException("Linkage not found");
        }
    }

    public List<Person> findPeopleByBookId(int id) {
        return linkageRepository.findPeopleByBookId(id);
    }
}
