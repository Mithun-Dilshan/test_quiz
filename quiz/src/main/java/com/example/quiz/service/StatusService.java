package com.example.quiz.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.quiz.entity.Status;

@Service

public interface StatusService {
    Status creatStatus(Status ststus);
    List<Status> getAllStatus();

    
} 
