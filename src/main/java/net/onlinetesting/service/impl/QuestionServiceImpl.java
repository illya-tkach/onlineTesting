package net.onlinetesting.service.impl;

import net.onlinetesting.model.Question;
import net.onlinetesting.repository.QuestionRepository;
import net.onlinetesting.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {


    @Autowired
    QuestionRepository questionRepository;

    @Override
    public List<Question> getTenRandomQuestions(long testId) {

        return questionRepository.getTenRandomQuestions(testId);

    }
}
