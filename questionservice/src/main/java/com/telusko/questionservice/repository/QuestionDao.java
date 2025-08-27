package com.telusko.questionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.telusko.questionservice.entity.Question;

public interface QuestionDao extends JpaRepository<Question,Integer>{
	
	List<Question> findByCategory(String category);
    
	@Query(value="select q.id from question q where category=:category order by rand() limit :numQns", nativeQuery=true)
	List<Integer> getRandomQuestions(String category, Integer numQns);

}
