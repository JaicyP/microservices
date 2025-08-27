package com.telusko.questionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.questionservice.entity.Question;
import com.telusko.questionservice.entity.QuestionWrapper;
import com.telusko.questionservice.entity.Response;
import com.telusko.questionservice.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	@PostMapping("/create")
	public ResponseEntity<String> createQuestions(@RequestBody Question question) {
		return questionService.createQuestions(question);
	}
	
	@GetMapping("/getqns/{id}")
	public ResponseEntity<Question> getQuestionsById(@PathVariable int id){
		return questionService.getQuestionsById(id);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<Question>> getAllQuestions(){
		return questionService.getAllQuestions();
	}
	
	@GetMapping("/getbycate/{category}")
	public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
		return questionService.getQuestionsByCategory(category);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Question> updateQuestion(@PathVariable int id,@RequestBody Question question){
		return questionService.updateQuestion(id,question);
	}
	
	@GetMapping("/rndmqns")
	public ResponseEntity<List<Integer>> generateRandomQnsForQuiz(@RequestParam String category,
			@RequestParam Integer numQns){
		return questionService.generateRandomQnsForQuiz(category,numQns);
	}
	
	@PostMapping("/qnsforquiz")
	public ResponseEntity<List<QuestionWrapper>> getQnsFromList(@RequestBody List<Integer> list){
		return questionService.getQnsFromList(list);
	}
	
	@PostMapping("/score")
	public ResponseEntity<Integer> calculateResult(@RequestBody List<Response> responses){
		return questionService.caculateResult(responses);
	}
	
	
     
}
