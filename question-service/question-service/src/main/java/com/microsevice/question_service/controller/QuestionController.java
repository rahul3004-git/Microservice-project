package com.microsevice.question_service.controller;


import com.microsevice.question_service.entity.Question;
import com.microsevice.question_service.entity.QuestionWrapper;
import com.microsevice.question_service.entity.Response;
import com.microsevice.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    Environment environment;

    @GetMapping("allQuestion")
    public ResponseEntity<List<Question>> getAllQuestion() {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @PutMapping("updateQuestion")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
        return questionService.updateQuestion(question);
    }


    //generate
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions) {

        return questionService.getQuestionForQuiz(categoryName, numQuestions);
    }

    //getQuestions(questionid)
    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds) {
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsFromId(questionIds);
    }

    //get score
    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses) {
        return questionService.getScore(responses);
    }

}
