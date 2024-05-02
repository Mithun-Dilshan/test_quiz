package com.example.quiz.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.quiz.entity.QuizMarks;

@Service

public interface QuizMarksService {


    public List <QuizMarks> getAllQuizMarks();
    public QuizMarks creatQuizMarks(QuizMarks quizMarks);
    
}
