package com.sbb1.comment;

import com.sbb1.DataNotFoundException;
import com.sbb1.answer.Answer;
import com.sbb1.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment create(Answer answer, String content, SiteUser author) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreateDate(LocalDateTime.now());
        comment.setAnswer(answer);
        comment.setAuthor(author);
        this.commentRepository.save(comment);
        return comment;
    }

    public Comment getComment(Integer id) {
        Optional<Comment> comment = this.commentRepository.findById(id);
        if (comment.isPresent()) {
            return comment.get();
        } else {
            throw new DataNotFoundException("comment not found");
        }
    }

    public void modify(Comment comment, String content) {
        comment.setContent(content);
        comment.setModifyDate(LocalDateTime.now());
        this.commentRepository.save(comment);
    }

    public void delete(Comment comment) {
        this.commentRepository.delete(comment);
    }
}