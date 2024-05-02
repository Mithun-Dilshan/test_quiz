package com.example.quiz.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz.entity.Quiz;
import com.example.quiz.entity.QuizMarks;
import com.example.quiz.service.QuizMarksService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class QuizMarksControler {

    private QuizMarksService quizMarksService;


    @Autowired
    public QuizMarksControler(QuizMarksService quizMarksService){
        this.quizMarksService = quizMarksService;
    }
    @GetMapping("/quizmarks")
    public ResponseEntity<List<QuizMarks>> getAllQuizMarks(){
        return ResponseEntity.status(HttpStatus.OK).body(quizMarksService.getAllQuizMarks());
    }


    @PostMapping("/quizmarks")
    public ResponseEntity<QuizMarks>creatQuizMarks(@RequestBody QuizMarks quizMarks  ){
        try {
            System.out.println("ok");
            System.out.println(quizMarks);

            QuizMarks newQuizMarks =quizMarksService.creatQuizMarks(quizMarks);
            return ResponseEntity.status(HttpStatus.CREATED).body(newQuizMarks);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

            
        }
    }


    
}
