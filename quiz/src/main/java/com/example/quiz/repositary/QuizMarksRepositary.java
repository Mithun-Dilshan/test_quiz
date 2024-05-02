package com.example.quiz.repositary;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.quiz.entity.QuizMarks;

public interface QuizMarksRepositary extends MongoRepository <QuizMarks, String> {


    
}
