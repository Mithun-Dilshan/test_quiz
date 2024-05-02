package com.example.quiz.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection ="usermarks")
@Data

public class QuizMarks {
    String id;
    int userid;
    int a_cat;
    int b_cat;
    int c_cat;
    
}
