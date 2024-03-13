package com.example.quiz.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection ="useranswer")
@Data

public class UserAnswer {
    String id;
    String quiz;
    int UserOptionId;
    boolean UsreAnswer;

}
