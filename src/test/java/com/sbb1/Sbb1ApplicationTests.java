package com.sbb1;

import com.sbb1.answer.Answer;
import com.sbb1.question.Question;
import com.sbb1.question.QuestionRepository;
import com.sbb1.question.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class Sbb1ApplicationTests {

    @Autowired
    private QuestionService questionService;

    @Test
    void testJpa() {
        for (int i = 1; i <= 300; i++) {
            String subject = String.format("테스트 데이터입니다: [%03d]", i);
            String content = "내용무";
            this.questionService.create(subject, content);
        }
    }
}