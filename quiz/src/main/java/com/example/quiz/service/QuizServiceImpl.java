package com.example.quiz.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quiz.entity.Quiz;
import com.example.quiz.repositary.QuizRepositary;

@Service

public class QuizServiceImpl implements QuizService{
    private QuizRepositary quizRepositary;

    @Autowired
    public QuizServiceImpl(QuizRepositary quizRepositary){
        this.quizRepositary = quizRepositary;


    }


    @Override 
    public List<Quiz> getAllQuiz(){
        return quizRepositary.findAll();
    }




    @Override
    public Quiz getQuizById(String id){
        return quizRepositary.findById(id).orElseThrow(() -> new NoSuchElementException("User not fond"+id));
    }
    @Override
    public Quiz createQuiz (Quiz quiz){
        return quizRepositary.save(quiz);
        
    }


    @Override
    public Quiz updateQuiz (String id, Quiz quiz){
        Quiz existingQuiz = getQuizById(id);
        existingQuiz.setTitle(quiz.getTitle());
        existingQuiz.setOption01(quiz.getOption01());
        existingQuiz.setOption02(quiz.getOption02());
        existingQuiz.setOption03(quiz.getOption03());
        existingQuiz.setOption04(quiz.getOption04());
        
       
        
        return quizRepositary.save(existingQuiz);

    } 

    @Override 
    public void deleteQuiz(String id){
        quizRepositary.deleteById(id);
    }

  
    @Override
    public Map<String, List<Quiz>> getQuizzesByBank() {
        Map<String, List<Quiz>> quizzesByBank = new HashMap<>();
    
        // Get all quizzes from the database
        List<Quiz> allQuizzes = (List<Quiz>) quizRepositary.findAll();
    
        // Group quizzes by bank
        Map<String, List<Quiz>> quizzesByBankTemp = allQuizzes.stream().collect(Collectors.groupingBy(Quiz::getType));
    
        // For each bank, select 5 random quizzes
        for (Map.Entry<String, List<Quiz>> entry : quizzesByBankTemp.entrySet()) {
            List<Quiz> quizzes = entry.getValue();
            Collections.shuffle(quizzes); // Shuffle the quizzes to get them randomly
            quizzesByBank.put(entry.getKey(), quizzes.subList(0, Math.min(5, quizzes.size())));
        }
    

        return quizzesByBank;
    }
}


    
