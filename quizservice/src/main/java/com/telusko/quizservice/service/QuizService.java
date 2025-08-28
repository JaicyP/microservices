package com.telusko.quizservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.telusko.quizservice.dao.QuizDao;
import com.telusko.quizservice.entity.QuestionWrapper;
import com.telusko.quizservice.entity.Quiz;
import com.telusko.quizservice.entity.Response;
import com.telusko.quizservice.feignclient.QuizInterface;

@Service
public class QuizService {
	
	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuizInterface quizInterface;
	

	public ResponseEntity<String> createQuiz(String category, String title, Integer numQns) {
		try {
		   List<Integer> questionId=quizInterface.generateRandomQnsForQuiz(category, numQns).getBody();
		   Quiz quiz=new Quiz();
		   quiz.setQTitle(title);
		   quiz.setQuestionIds(questionId);
		   quizDao.save(quiz);
		   return new ResponseEntity<>("sucess",HttpStatus.CREATED);
	    }catch(Exception e) {
	    	e.printStackTrace();
		}
		return new ResponseEntity<>("Try Again",HttpStatus.BAD_REQUEST);
	}


	public ResponseEntity<List<QuestionWrapper>> getQuestions(int id) {
		  Quiz quiz=quizDao.findById(id).get();
		  List<Integer> list=quiz.getQuestionIds();
		try {
			List<QuestionWrapper> qnList= quizInterface.getQnsFromList(list).getBody();	
			return new ResponseEntity<>(qnList,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}


	public ResponseEntity<Integer> getScore(int id, List<Response> responses) {
		ResponseEntity<Integer> score=quizInterface.calculateResult(responses);
		return score;
	}

}
