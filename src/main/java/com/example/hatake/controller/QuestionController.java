package com.example.hatake.controller;


import com.example.hatake.model.Questions;
import com.example.hatake.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class QuestionController {
    @Autowired
    private QuestionRepo questionRepository;

    @GetMapping("/")
    public Page<Questions> getQuestions(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }


    @PostMapping("/questions")
    public Questions createQuestions(@Valid @RequestBody Questions question) {
        return questionRepository.save(question);
    }

    @GetMapping("/questions/{questionId}")
    public Optional<Questions> getQuestions(@PathVariable Long questionId) {
        return questionRepository.findById(questionId);
    }

    @DeleteMapping("/deletequestions/{questionId}")
    public Optional<ResponseEntity<Object>> deleteQuestions(@PathVariable Long questionId) {
        return questionRepository.findById(questionId)
                .map(question -> {
                    questionRepository.delete(question);
                    return ResponseEntity.ok().build();
                });


    }



    @PutMapping("/edit/{questionId}")
    public Optional<Questions> updateQuestion(@PathVariable Long questionId,
                                              @Valid @RequestBody Questions questionRequest) {
        return questionRepository.findById(questionId)
                .map(question -> {
                    question.setTitle(questionRequest.getTitle());
                    question.setDescription(questionRequest.getDescription());
                    return questionRepository.save(question);
                });
    }

}