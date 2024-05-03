package com.example.quiz.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quiz.entity.PlayerMarksEntity;

import com.example.quiz.repositary.PlayerMarksRepository;

@Service
public class PlayerMarksServiceImpl implements PlayerMarksService {
    @Autowired
    private PlayerMarksRepository playerMarksRepository;

    @Override
    public void savePlayerMarks(PlayerMarksEntity playerMarks) {
        playerMarksRepository.save(playerMarks);
    }

    @Override
    public List<PlayerMarksEntity> getAllPlayerMarks() {
        return playerMarksRepository.findAll();
    }

    @Override
    public PlayerMarksEntity getLatestPlayerMarks() {
        return playerMarksRepository.findTopByOrderByCreatedAtDesc();
    }

     @Override
    public PlayerMarksEntity getPlayerMarksById(String id){
        return playerMarksRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not fond"+id));
    }
}
