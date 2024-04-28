package com.quizweb.quizweb.service;

import com.quizweb.quizweb.entity.Question;
import com.quizweb.quizweb.entity.QuestionWrapper;
import com.quizweb.quizweb.entity.Quiz;
import com.quizweb.quizweb.entity.Response;
import com.quizweb.quizweb.repository.QuestionRepository;
import com.quizweb.quizweb.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<String> createQuizByRandom(String category, Integer numQ, String title) {
        List<Question> questions = questionRepository.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public List<QuestionWrapper> displayQuiz(Integer id) {
        Quiz quz = quizRepository.findById(id).get();
        List<Question> questionsFromDb = quz.getQuestions();

        List<QuestionWrapper> questionForUser = new ArrayList<>();

        for (Question question : questionsFromDb) {
            QuestionWrapper qw = new QuestionWrapper(question.getId(), question.getQuestionTitle(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4());
            questionForUser.add(qw);
        }
        return questionForUser;
    }

    public Integer calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int right = 0;
        int i = 0;
        for (Response response : responses) {
            if (response.getResponse().equals(questions.get(i).getCorrectAnswer())) {
                right++;
            }
            i++;
        }
        return right;
    }
}
