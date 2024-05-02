package com.example.quiz.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection ="quiz")
@Data


public class Quiz {
    private String id;
    private String title;
    private String option01;
    private String option02;
    private String option03;
    private String option04;
    private  String answer;
    private String type;

    
}
