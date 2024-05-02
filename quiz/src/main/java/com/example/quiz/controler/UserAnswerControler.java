package com.example.quiz.controler;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz.entity.UserAnswer;

import com.example.quiz.service.UserAnswerService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")

public class UserAnswerControler {


    

    private UserAnswerService userAnswerService;

    @Autowired
    public UserAnswerControler(UserAnswerService UserAnswerService){
        this.userAnswerService =userAnswerService;
    } 
    @GetMapping("/useranswer")
    public ResponseEntity<List<UserAnswer>> getAllUserAnswer(){
        return ResponseEntity.status(HttpStatus.OK).body(userAnswerService.getAllUserAnswer());
    }

    @GetMapping("/useranswer/{id}")
    public ResponseEntity<UserAnswer> getUserAnswerById(@PathVariable String id){
        try {
            UserAnswer userAnswer =userAnswerService.getUserAnswerById(id);
            return ResponseEntity.status(HttpStatus.OK).body(userAnswer); 
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);


        }
        catch(Exception  e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @PostMapping("/useranswer")
    public ResponseEntity<UserAnswer>creatUserAnswer(@RequestBody UserAnswer userAnswer  ){
        try {
            System.out.println("ok");
            System.out.println(userAnswer);

            UserAnswer newUserAnswer =userAnswerService.creatUserAnswer(userAnswer);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUserAnswer);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

            
        }
    }
    
}
