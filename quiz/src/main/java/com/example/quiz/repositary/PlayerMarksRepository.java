package com.example.quiz.repositary;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz.entity.PlayerMarksEntity;

@Repository
public interface PlayerMarksRepository extends MongoRepository<PlayerMarksEntity, String> {
    PlayerMarksEntity findTopByOrderByCreatedAtDesc();
}
