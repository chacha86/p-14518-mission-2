package com.sbb1.comment;

import com.sbb1.answer.Answer;
import com.sbb1.answer.AnswerService;
import com.sbb1.user.SiteUser;
import com.sbb1.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RequestMapping("/comment")
@RequiredArgsConstructor
@Controller
public class CommentController {

    private final CommentService commentService;
    private final AnswerService answerService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createComment(@PathVariable("id") Integer id,
                               @Valid CommentForm commentForm, BindingResult bindingResult, Principal principal) {
        Answer answer = this.answerService.getAnswer(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());
        if (bindingResult.hasErrors()) {
            return String.format("redirect:/question/detail/%s#answer_%s",
                    answer.getQuestion().getId(), answer.getId());
        }
        Comment comment = this.commentService.create(answer, commentForm.getContent(), siteUser);
        return String.format("redirect:/question/detail/%s#answer_%s",
                answer.getQuestion().getId(), answer.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String modifyComment(@PathVariable("id") Integer id,
                               @Valid CommentForm commentForm, BindingResult bindingResult, Principal principal) {
        Comment comment = this.commentService.getComment(id);
        if (!comment.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        if (bindingResult.hasErrors()) {
            return String.format("redirect:/question/detail/%s#answer_%s",
                    comment.getAnswer().getQuestion().getId(), comment.getAnswer().getId());
        }
        this.commentService.modify(comment, commentForm.getContent());
        return String.format("redirect:/question/detail/%s#answer_%s",
                comment.getAnswer().getQuestion().getId(), comment.getAnswer().getId());
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/delete/{id}")
    public String deleteComment(Principal principal, @PathVariable("id") Integer id) {
        Comment comment = this.commentService.getComment(id);
        if (!comment.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        Answer answer = comment.getAnswer();
        this.commentService.delete(comment);
        return String.format("redirect:/question/detail/%s#answer_%s",
                answer.getQuestion().getId(), answer.getId());
    }
}