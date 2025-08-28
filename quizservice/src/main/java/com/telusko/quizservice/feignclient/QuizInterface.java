package com.telusko.quizservice.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.telusko.quizservice.entity.QuestionWrapper;
import com.telusko.quizservice.entity.Response;


@FeignClient("QUESTIONSERVICE")
public interface QuizInterface {
	
	@GetMapping("/question/rndmqns")
	public ResponseEntity<List<Integer>> generateRandomQnsForQuiz(@RequestParam String category,
			@RequestParam Integer numQns);
	
	@PostMapping("/question/qnsforquiz")
	public ResponseEntity<List<QuestionWrapper>> getQnsFromList(@RequestBody List<Integer> list);
	
	@PostMapping("/question/score")
	public ResponseEntity<Integer> calculateResult(@RequestBody List<Response> responses);

}
