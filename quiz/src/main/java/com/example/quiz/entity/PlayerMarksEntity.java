package com.example.quiz.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "player_marks")
@Data

public class PlayerMarksEntity {
   
    private String id;
    private String userid;
    private int[] marksCategoryA;
  
       @CreatedDate
    private LocalDateTime createdAt;

    // Constructors, getters, and setters
}
