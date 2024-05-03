package com.example.quiz.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection ="status")
@Data

public class Status {
   private String id;
   private String userid;
   private float marks;
   private String status;    
    
}
