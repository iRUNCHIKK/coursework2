package com.skypro.coursework.service;

import com.skypro.coursework.model.Question;

import java.util.Collection;

public interface ExaminerService  {

    Collection<Question> getQuestions(int amount);

}
