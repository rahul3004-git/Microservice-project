package com.microsevice.quiz_service.dao;

import com.microsevice.quiz_service.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz,Integer> {
}
