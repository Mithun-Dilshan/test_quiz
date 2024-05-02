package com.example.quiz.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.quiz.entity.PlayerMarksEntity;


@Service
public interface PlayerMarksService {
    void savePlayerMarks(PlayerMarksEntity playerMarks);
    List<PlayerMarksEntity> getAllPlayerMarks();
    PlayerMarksEntity getLatestPlayerMarks();
}
