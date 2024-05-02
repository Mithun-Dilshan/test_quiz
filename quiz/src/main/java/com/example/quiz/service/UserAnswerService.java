package com.example.quiz.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.quiz.entity.UserAnswer;

@Service

public interface UserAnswerService {
    List<UserAnswer> getAllUserAnswer();
    UserAnswer getUserAnswerById(String id);
    UserAnswer creatUserAnswer(UserAnswer useranswer);
    
}
