package com.telusko.questionservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.telusko.questionservice.entity.Question;
import com.telusko.questionservice.entity.QuestionWrapper;
import com.telusko.questionservice.entity.Response;
import com.telusko.questionservice.repository.QuestionDao;


@Service
public class QuestionService {
	
	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<String> createQuestions(Question question) {
		try {
			questionDao.save(question);
		    return new ResponseEntity<>("sucess",HttpStatus.CREATED) ;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Try again",HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<Question> getQuestionsById(int id) {
	   try {
		       Question question=questionDao.findById(id).get();
		       return new ResponseEntity<>(question,HttpStatus.OK);
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
		return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
			List<Question> questions=questionDao.findAll();
			return new ResponseEntity<>(questions,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
		try {
			List<Question> questions=questionDao.findByCategory(category);
			return new ResponseEntity<>(questions,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<Question> updateQuestion(int id, Question question) {
		try {
			Question existing=questionDao.findById(id).get();
			existing.setQuestionTitle(question.getQuestionTitle());
			questionDao.save(existing);
			return new ResponseEntity<>(existing,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity(null,HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Integer>> generateRandomQnsForQuiz(String category, Integer numQns) {
		try {
			List<Integer> qnNumList=questionDao.getRandomQuestions(category,numQns);
			return new ResponseEntity<>(qnNumList,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<QuestionWrapper>> getQnsFromList(List<Integer> list) {
		List<QuestionWrapper> qnList=new ArrayList<>();
		
		try {
			for(Integer i:list) {
				Question ques=questionDao.findById(i).get();
				QuestionWrapper qw=new QuestionWrapper();
				System.out.println(ques.getQuestionTitle());
				qw.setId(ques.getId());
				qw.setQuestiontitle(ques.getQuestionTitle());
				qw.setOption1(ques.getOption1());
				qw.setOption2(ques.getOption2());
				qw.setOption3(ques.getOption3());
				qw.setOption4(ques.getOption4());
				qnList.add(qw);
			}
			return new ResponseEntity<>(qnList,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<Integer> caculateResult(List<Response> responses){
		try {
			int right=0;
			for(Response res:responses) {
				Question question=questionDao.findById(res.getId()).get();
				if(question.getRightAnswer().equals(res.getResponse())) {
					right++;
				}
			}
			return new ResponseEntity<>(right,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(-1,HttpStatus.BAD_REQUEST);
	}
	
	

}
