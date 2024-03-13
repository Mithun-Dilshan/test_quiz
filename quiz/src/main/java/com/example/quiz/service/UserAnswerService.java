package com.example.quiz.service;

import java.util.List;

import com.example.quiz.entity.UserAnswer;

public interface UserAnswerService {
       List<UserAnswer> getAllUserAnswer();
    UserAnswer getUserAnswerById(String id);
    UserAnswer creatUserAnswer(UserAnswer useranswer);

    
    
}
