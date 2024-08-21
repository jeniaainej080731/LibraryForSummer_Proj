package com.example.LibraryForSummer.repositories.forChilds;

import com.example.LibraryForSummer.models.Childs.History;
import com.example.LibraryForSummer.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HistoryRepository extends JpaRepository<History, Integer> {
    List<History> findByPersonId(int personId);
    List<History> findByBookId(int bookId);
    List<History> findByPersonIdAndBookId(int personId, int bookId);
    @Query("select h.person from History h where h.book.id = :bookId")
    List<Person> findPeopleByBookId(@Param("bookId")int bookId);
}
