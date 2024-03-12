package com.example.quiz.repositary;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.quiz.entity.Quiz;

public interface QuizRepositary extends MongoRepository<Quiz , String> {

    // List<Quiz> findByType(String type);
    
}
