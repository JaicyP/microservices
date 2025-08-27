package com.telusko.questionservice.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionWrapper {
  
  private Integer id;
  private String questiontitle;
  private String option1;
  private String option2;
  private String option3;
  private String option4;
}
