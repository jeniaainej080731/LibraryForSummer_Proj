package com.example.LibraryForSummer.services.forParents;

import com.example.LibraryForSummer.models.Parents.Comments;
import com.example.LibraryForSummer.repositories.forParents.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CommentsService {
    private final CommentsRepository commentsRepository;

    @Autowired
    public CommentsService(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    public Comments findById(int id) {
        Optional<Comments> comments = commentsRepository.findById(id);
        return comments.orElse(null);
    }

    @Transactional
    public void save(Comments comments) {
        commentsRepository.save(comments);
    }

    @Transactional
    public void update(int id, Comments comments) {
        comments.setCommentId(id);
        commentsRepository.save(comments);
    }

    @Transactional
    public void delete(int id) {
        commentsRepository.deleteById(id);
    }

    public Optional<Comments> findByComment(String comment) {
        return commentsRepository.findByComment(comment);
    }

    public List<Comments> findAllCommentsByBookId(int bookId) {
        return commentsRepository.findAllCommentsByBookId(bookId);
    }

    public Page<Comments> findAllCommentsByBookId(int bookId, Pageable pageable) {
        return commentsRepository.findAllCommentsByBookId(bookId, pageable);
    }
}
