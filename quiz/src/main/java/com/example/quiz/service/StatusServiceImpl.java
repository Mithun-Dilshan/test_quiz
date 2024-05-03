package com.example.quiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import com.example.quiz.entity.Status;
import com.example.quiz.repositary.StatusRepositary;






public class StatusServiceImpl implements StatusService {
    private StatusRepositary statusRepositary;

    @Autowired
    public StatusServiceImpl (StatusRepositary statusRepositary){
        this.statusRepositary = statusRepositary ;
    }


    @Override
    public Status creatStatus(Status status){
        return statusRepositary.save(status);
        
    }

       @Override 
    public List<Status> getAllStatus(){
        return statusRepositary.findAll();
    }




   
    
}