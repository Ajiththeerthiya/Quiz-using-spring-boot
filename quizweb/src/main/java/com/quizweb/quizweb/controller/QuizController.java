package com.quizweb.quizweb.controller;

import com.quizweb.quizweb.entity.QuestionWrapper;
import com.quizweb.quizweb.entity.Response;
import com.quizweb.quizweb.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title) {
        return quizService.createQuizByRandom(category, numQ, title);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizById(@PathVariable Integer id) {
        return new ResponseEntity<>(quizService.displayQuiz(id), HttpStatus.FOUND);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> response) {
        return new ResponseEntity<>(quizService.calculateResult(id, response), HttpStatus.OK);
    }
}
