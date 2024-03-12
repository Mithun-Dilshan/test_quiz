package com.example.quiz.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.quiz.entity.Quiz;

@Service

public interface QuizService {
    List<Quiz> getAllQuiz();
    Quiz getQuizById(String id);
    Quiz createQuiz(Quiz quiz);
    Quiz updateQuiz(String id , Quiz quiz);
    void deleteQuiz (String id);

    Map<String, List<Quiz>> getQuizzesByBank();


    
}
