package com.example.hatake.repository;

import com.example.hatake.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionRepo extends JpaRepository<Questions, Long> {
}
