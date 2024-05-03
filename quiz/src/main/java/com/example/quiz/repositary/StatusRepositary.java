package com.example.quiz.repositary;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.quiz.entity.Status;



public interface StatusRepositary extends MongoRepository<Status, String>  {


    
} 
