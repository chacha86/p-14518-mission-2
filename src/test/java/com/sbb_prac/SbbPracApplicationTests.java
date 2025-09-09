package com.sbb_prac;

import com.sbb_prac.answer.Answer;
import com.sbb_prac.answer.AnswerRepository;
import com.sbb_prac.question.Question;
import com.sbb_prac.question.QuestionRepository;
import com.sbb_prac.question.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SbbPracApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionService questionService;

    @Test
    void testJpa() {
        List<Question> all = this.questionRepository.findAll();
        assertEquals(2,all.size());

        Question q = all.get(0);
        assertEquals("sbb가 무엇인가요?", q.getSubject());
    }

    @Test
    void testJpa2() {
        Optional<Question> oq = this.questionRepository.findById(1);
        if(oq.isPresent()) {
            Question q = oq.get();
            assertEquals("sbb가 무엇인가요?",q.getSubject());
        }
    }


    @Test
    void testJpa3() {
        Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
        assertEquals(1,q.getId());
    }

    @Test
    void testJpa4() {
        Question q = this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요?","sbb에 대해서 알고 싶습니다.");
        assertEquals(1,q.getId());
    }

    @Test
    void testJpa5() {
        List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
        Question q = qList.get(0);
        assertEquals("sbb가 무엇인가요?",q.getSubject());
    }

    @Test
    void testJpa6() {
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        q.setSubject("수정된 제목");
        this.questionRepository.save(q);
    }

    @Test
    void testJpa7() {
        assertEquals(2,this.questionRepository.count());
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        this.questionRepository.delete(q);
        assertEquals(1,this.questionRepository.count());
    }

    @Test
    void testJpa8() {
        Optional<Question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        Answer a = new Answer();
        a.setContent("네 자동으로 생성됩니다.");
        a.setQuestion(q);
        a.setCreateDate(LocalDateTime.now());
        this.answerRepository.save(a);
    }

    @Test
    void testJpa9() {
        Optional<Answer> oa = this.answerRepository.findById(1);
        assertTrue(oa.isPresent());
        Answer a = oa.get();
        assertEquals(2,a.getQuestion().getId());
    }

    @Transactional
    @Test
    void testJpa10() {
        Optional<Question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        List<Answer> answerList = q.getAnswerList();

        assertEquals(1, answerList.size());
        assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
    }

    @Test
    void testJpa11() {
        for(int i=1;i<=300;i++) {
            String subject = String.format("테스트 데이터입니다 : [%03d]", i);
            String content = "내용무";
            this.questionService.create(subject, content,null);
        }
    }
}