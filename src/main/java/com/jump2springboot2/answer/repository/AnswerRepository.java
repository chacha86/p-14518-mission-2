package com.jump2springboot2.answer.repository;

import com.jump2springboot2.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
