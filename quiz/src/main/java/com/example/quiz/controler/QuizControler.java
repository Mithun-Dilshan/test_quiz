package com.example.quiz.controler;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz.entity.Quiz;
import com.example.quiz.service.QuizService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
//hello

public class QuizControler {




    private QuizService quizService;

    @Autowired
    public QuizControler(QuizService quizService){
        this.quizService =quizService;
    } 
    @GetMapping("/quiz")
    public ResponseEntity<List<Quiz>> getAllQuiz(){
        return ResponseEntity.status(HttpStatus.OK).body(quizService.getAllQuiz());
    }

    @GetMapping("/quiz/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable String id){
        try {
            Quiz quiz =quizService.getQuizById(id);
            return ResponseEntity.status(HttpStatus.OK).body(quiz); 
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);


        }
        catch(Exception  e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @PostMapping("/quiz")
    public ResponseEntity<Quiz>creatQuiz(@RequestBody Quiz quiz  ){
        try {
            System.out.println("ok");
            System.out.println(quiz);

            Quiz newQuiz =quizService.createQuiz(quiz);
            return ResponseEntity.status(HttpStatus.CREATED).body(newQuiz);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

            
        }
    }
    @PutMapping("/quiz/{id}")
    public ResponseEntity<Quiz>updateQuiz(@PathVariable String id,@RequestBody Quiz quiz){
        try {
            Quiz updateQuiz = quizService.updateQuiz(id, quiz);
            return  ResponseEntity.status(HttpStatus.OK).body(updateQuiz);
        
            
        } catch(NoSuchElementException e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        catch(Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/quiz/{id}")
    public ResponseEntity<Void>deleteQuiz(@PathVariable String id){
        try {
            quizService.deleteQuiz(id);
            return  ResponseEntity.status(HttpStatus.OK).body(null); 
            
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            
        }
    }

    @GetMapping("/banks")
    public Map<String, List<Quiz>> getQuizzesByBank() {
        return quizService.getQuizzesByBank();
    }

    
    
}
