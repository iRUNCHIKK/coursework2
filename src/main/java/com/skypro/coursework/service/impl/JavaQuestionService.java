package com.skypro.coursework.service.impl;

import com.skypro.coursework.model.Question;
import com.skypro.coursework.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions = new HashSet<>();

    private final Random random = new Random();

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        boolean isRemoved = questions.remove(question);
        if (isRemoved) {
            return question;
        } else {
            return null;
        }
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(questions);
    }

    @Override
    public Question getRandomQuestion() {
        int index = random.nextInt(questions.size());
        return new ArrayList<>(questions).get(index);
    }
}
