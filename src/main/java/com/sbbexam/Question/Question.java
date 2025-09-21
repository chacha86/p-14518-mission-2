package com.sbbexam.Question;

import com.sbbexam.Answer.Answer;
import com.sbbexam.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 200)
	private String subject;

	@Column(columnDefinition = "TEXT")
	private String content;

	private LocalDateTime createDate;

	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
	private List<Answer> answerList;

    @ManyToOne // 사용자 한 명이 질문을 여러 개 작성
    private SiteUser author;

    private LocalDateTime modifyDate;

    @ManyToMany
    private Set<SiteUser> voter; // 속성값이 중복되지 않기 위새 Set 사용
}