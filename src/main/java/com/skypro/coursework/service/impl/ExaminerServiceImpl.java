package com.skypro.coursework.service.impl;

import com.skypro.coursework.exception.NotEnoughQuestionsException;
import com.skypro.coursework.model.Question;
import com.skypro.coursework.service.ExaminerService;
import com.skypro.coursework.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl (QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        int questionTotalSize = questionService.getAll().size();
        if (amount > questionTotalSize) {
            throw new NotEnoughQuestionsException("Запрошено " + amount + " вопросов, доступно " + questionTotalSize);
        }

        Set<Question> randomQuestions = new HashSet<>();

        while (randomQuestions.size() < amount) {
            randomQuestions.add(questionService.getRandomQuestion());
        }

        return randomQuestions;
    }
}
