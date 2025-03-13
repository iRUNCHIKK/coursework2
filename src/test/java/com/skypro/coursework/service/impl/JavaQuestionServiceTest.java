package com.skypro.coursework.service.impl;

import com.skypro.coursework.model.Question;
import com.skypro.coursework.service.QuestionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {

    private QuestionService questionService;

    @BeforeEach
    public void clear() {
        questionService = new JavaQuestionService();
    }

    @Test
    @DisplayName("Корректно добавляет вопросы")
    void add() {
        Question expected = new Question("question", "answer");
        //test
        Question actual = questionService.add(expected.getQuestion(), expected.getAnswer());
        //check
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Корректно удаляет вопросы")
    void remove() {
        Question expected = new Question("question", "answer");
        questionService.add(expected.getQuestion(), expected.getAnswer());
        //test
        Question actual = questionService.remove(expected);
        //check
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Корректно выводит все вопросы")
    void getAll() {
        Question question1 = new Question("question", "answer");
        Question question2 = new Question("question", "answer");

        questionService.add(question1);
        questionService.add(question2);

        Set<Question> expectedQuestions = new HashSet<>() {{
            add(question1);
            add(question2);
        }};

        //test
        Collection<Question> actual = questionService.getAll();
        //check
        assertEquals(expectedQuestions, actual);
    }
}