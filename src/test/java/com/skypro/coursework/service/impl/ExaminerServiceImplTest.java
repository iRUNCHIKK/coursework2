package com.skypro.coursework.service.impl;

import com.skypro.coursework.exception.NotEnoughQuestionsException;
import com.skypro.coursework.model.Question;
import com.skypro.coursework.service.QuestionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    @DisplayName("Корректно выводит рандомные вопросы")
    void getQuestions() {
        int amount = 3;

        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            questions.add(new Question("question" + i, "answer" + i));
        }

        when(questionService.getAll()).thenReturn(questions);

        when(questionService.getRandomQuestion()).thenReturn(
                questions.get(0), questions.get(0),
                questions.get(1), questions.get(1),
                questions.get(1), questions.get(2)
        );
        //test
        Collection<Question> randomQuestions = examinerService.getQuestions(amount);
        //check
        assertEquals(randomQuestions.size(), amount);
        assertTrue(randomQuestions.containsAll(questions));

        verify(questionService, times(6)).getRandomQuestion();
    }

    @Test
    @DisplayName("Выбрасывает ошибку при нехватке вопросов")
    void questions() {
        int amount =3;

        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < amount / 2; i++) {
            questions.add(new Question("question" + i, "answer" + i));
        }

        when(questionService.getAll()).thenReturn(questions);
        //check
        assertThrows(
                NotEnoughQuestionsException.class,
                () -> examinerService.getQuestions(amount)
        );
    }
}