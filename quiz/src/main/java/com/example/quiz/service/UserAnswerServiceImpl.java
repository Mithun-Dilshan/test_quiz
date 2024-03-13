package com.example.quiz.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quiz.entity.UserAnswer;
import com.example.quiz.repositary.UserAnsweRepositary;

@Service
public class UserAnswerServiceImpl implements UserAnswerService {
    private UserAnsweRepositary userAnsweRepositary;

    @Autowired
    public UserAnswerServiceImpl(UserAnsweRepositary userAnsweRepositary){
        this.userAnsweRepositary = userAnsweRepositary;
    }

    public List<UserAnswer> getAllUserAnswer() {
        return userAnsweRepositary.findAll();
    }

    @Override
    public UserAnswer getUserAnswer(String id) {
        return userAnsweRepositary.findById(id).orElseThrow(() -> new NoSuchElementException("User not found: " + id));
    }

    @Override
    public UserAnswer createUserAnswer(UserAnswer userAnswer) {
        return userAnsweRepositary.save(userAnswer);
    }
}
