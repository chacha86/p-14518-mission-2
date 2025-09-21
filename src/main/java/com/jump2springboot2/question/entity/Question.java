package com.jump2springboot2.question.entity;

import com.jump2springboot2.answer.entity.Answer;
import com.jump2springboot2.user.entity.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    @ManyToOne
    private SiteUser author;

    @ManyToMany
    Set<SiteUser> voter;

    @OneToMany(mappedBy = "question", fetch=FetchType.LAZY, cascade = {CascadeType.REMOVE,  CascadeType.PERSIST})
    private List<Answer> answerList = new ArrayList<>();

    public void addAnswer(String content) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setQuestion(this);
        this.answerList.add(answer);
    }
}
