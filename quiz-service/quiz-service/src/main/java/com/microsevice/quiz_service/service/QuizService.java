package com.microsevice.quiz_service.service;


import com.microsevice.quiz_service.dao.QuizDao;
import com.microsevice.quiz_service.entity.QuestionWrapper;
import com.microsevice.quiz_service.entity.Quiz;
import com.microsevice.quiz_service.entity.Response;
import com.microsevice.quiz_service.feign.QuizInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;

    @Autowired
    private QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Integer> questions = quizInterface.getQuestionForQuiz(category, numQ).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Quiz quiz = quizDao.findById(id).get();
        List<Integer> questionIds = quiz.getQuestionIds();

        ResponseEntity<List<QuestionWrapper>> questionWrappers = quizInterface.getQuestionsFromId(questionIds);
        return questionWrappers;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {

        return quizInterface.getScore(responses);
    }
}
