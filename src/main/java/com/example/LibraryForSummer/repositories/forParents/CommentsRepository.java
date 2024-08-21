package com.example.LibraryForSummer.repositories.forParents;

import com.example.LibraryForSummer.models.Parents.Comments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentsRepository extends JpaRepository<Comments, Integer> {
    Optional<Comments> findByComment(String comment);
    List<Comments> findAllCommentsByBookId(int bookId);
    Page<Comments> findAllCommentsByBookId(int bookId, Pageable pageable);
}
