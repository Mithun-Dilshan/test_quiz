package com.example.quiz.controler;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz.entity.PlayerMarksEntity;

import com.example.quiz.service.PlayerMarksService;

@RestController
@RequestMapping("/api/")
public class PlayerMarksController {
    @Autowired
    private PlayerMarksService playerMarksService;

    @PostMapping("/save")
    public ResponseEntity<String> savePlayerMarks(@RequestBody PlayerMarksEntity playerMarks) {
        playerMarksService.savePlayerMarks(playerMarks);
        return ResponseEntity.status(HttpStatus.CREATED).body("Player marks saved successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlayerMarksEntity>> getAllPlayerMarks() {
        List<PlayerMarksEntity> playerMarksList = playerMarksService.getAllPlayerMarks();
        return ResponseEntity.ok(playerMarksList);
    }

    @GetMapping("/latest")
    public ResponseEntity<PlayerMarksEntity> getLatestPlayerMarks() {
        PlayerMarksEntity latestPlayerMarks = playerMarksService.getLatestPlayerMarks();
        if (latestPlayerMarks != null) {
            return ResponseEntity.ok(latestPlayerMarks);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

     @GetMapping("/marks/{id}")
    public ResponseEntity<PlayerMarksEntity> getQuizById(@PathVariable String id){
        try {
            PlayerMarksEntity playerMarksEntity =playerMarksService.getPlayerMarksById(id);
            return ResponseEntity.status(HttpStatus.OK).body(playerMarksEntity); 
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);


        }
        catch(Exception  e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}