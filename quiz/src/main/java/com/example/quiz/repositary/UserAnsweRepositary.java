package com.example.quiz.repositary;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.quiz.entity.UserAnswer;
import java.util.List;

public interface UserAnsweRepositary extends MongoRepository <UserAnswer ,String> {
 
} 
