package com.example.LibraryForSummer.services.forChilds;

import com.example.LibraryForSummer.models.Childs.History;
import com.example.LibraryForSummer.models.Person;
import com.example.LibraryForSummer.repositories.forChilds.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class HistoryService {
    private final HistoryRepository historyRepository;

    @Autowired
    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public List<History> findAll() {
        return historyRepository.findAll();
    }

    public History findById(int id) {
        return historyRepository.findById(id).get();
    }

    @Transactional
    public void save(History history) {
        historyRepository.save(history);
    }

    @Transactional
    public void delete(int id) {
        historyRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, History history) {
        history.setId(id);
        historyRepository.save(history);
    }

    public List<History> findByPersonId(int personId) {
        return historyRepository.findByPersonId(personId);
    }

    public List<History> findByBookId(int bookId) {
        return historyRepository.findByBookId(bookId);
    }

    public List<History> findByPersonIdAndBookId(int personId, int bookId) {
        return historyRepository.findByPersonIdAndBookId(personId, bookId);
    }

    public List<Person> findPeopleByBookId(int bookId) {
        return historyRepository.findPeopleByBookId(bookId);
    }
}
