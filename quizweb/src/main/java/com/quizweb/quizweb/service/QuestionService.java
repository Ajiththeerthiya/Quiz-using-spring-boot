package com.quizweb.quizweb.service;

import com.quizweb.quizweb.entity.Question;
import com.quizweb.quizweb.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public List<Question> getQuestion() {
        return questionRepository.findAll();
    }

    public void editQuestion(Integer id, Question question) {
        Optional<Question> ques = questionRepository.findById(id);
        if (ques.isPresent()) {
            Question q = ques.get();
            q.setQuestionTitle(question.getQuestionTitle());
            q.setOption1(question.getOption1());
            q.setOption2(question.getOption2());
            q.setOption3(question.getOption3());
            q.setOption4(question.getOption4());
            q.setCorrectAnswer(question.getCorrectAnswer());
            q.setDifficultyLevel(question.getCategory());
            q.setDifficultyLevel(question.getDifficultyLevel());
            questionRepository.save(q);
        }
    }

    public void deleteQuestionById(Integer id) {
        questionRepository.deleteById(id);
    }
}
