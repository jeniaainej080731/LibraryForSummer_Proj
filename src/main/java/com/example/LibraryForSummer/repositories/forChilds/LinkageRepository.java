package com.example.LibraryForSummer.repositories.forChilds;

import com.example.LibraryForSummer.models.Childs.Linkage;
import com.example.LibraryForSummer.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LinkageRepository extends JpaRepository<Linkage, Integer> {
    @Query("SELECT l.person FROM Linkage l WHERE l.book.id = :bookId")
    List<Person> findPeopleByBookId(@Param("bookId") int bookId);
}

