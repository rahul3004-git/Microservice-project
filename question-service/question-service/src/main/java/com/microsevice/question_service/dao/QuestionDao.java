package com.microsevice.question_service.dao;

import com.microsevice.question_service.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

    List<Question> findByCategory(String category);

    @Query(value = "SELECT q.id FROM question q where q.category =:category ORDER BY RAND() LIMIT :numQuestions", nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, int numQuestions);
}

