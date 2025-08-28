package com.telusko.quizservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.quizservice.entity.QuestionWrapper;
import com.telusko.quizservice.entity.QuizDto;
import com.telusko.quizservice.entity.Response;
import com.telusko.quizservice.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	@PostMapping("/create")
	public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {
		return quizService.createQuiz(quizDto.getCategory(),quizDto.getTitle(),quizDto.getNumQns());
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuestions(@PathVariable int id){
		return quizService.getQuestions(id);
	}
	
	@PostMapping("/submit/{id}")
	public ResponseEntity<Integer>  getScore(@PathVariable int id,@RequestBody List<Response> responses){
		return quizService.getScore(id,responses);
	}

}
