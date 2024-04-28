package com.quizweb.quizweb.controller;

import com.quizweb.quizweb.entity.Question;
import com.quizweb.quizweb.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ques")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("/hi")
    public ResponseEntity<String> create() {
        return new ResponseEntity<>("Hii", HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return new ResponseEntity<>(questionService.createQuestion(question), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Question>> getAllQuestion() {
        List<Question> questions = questionService.getQuestion();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateQuestion(@PathVariable Integer id, @RequestBody Question question) {
        questionService.editQuestion(id, question);
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }

    @DeleteMapping("del/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id) {
        questionService.deleteQuestionById(id);
        return new ResponseEntity<>("Deleted id " + id, HttpStatus.OK);
    }
}
