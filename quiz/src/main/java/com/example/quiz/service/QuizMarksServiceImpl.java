package com.example.quiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quiz.entity.QuizMarks;
import com.example.quiz.repositary.QuizMarksRepositary;

@Service

public class QuizMarksServiceImpl implements QuizMarksService{
    private QuizMarksRepositary quizMarksRepositary;

    @Autowired
    public QuizMarksServiceImpl(QuizMarksRepositary quizMarksRepositary){
        this.quizMarksRepositary = quizMarksRepositary;

    }
    @Override
    public List<QuizMarks> getAllQuizMarks(){
        return quizMarksRepositary.findAll();
    }

    @Override
    public QuizMarks creatQuizMarks (QuizMarks quizMarks){
        return quizMarksRepositary.save(quizMarks);
    }
    
}
