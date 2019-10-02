package net.onlinetesting.service.impl;

import net.onlinetesting.dto.AnswerDTO;
import net.onlinetesting.dto.QuestionDTO;
import net.onlinetesting.model.Test;
import net.onlinetesting.repository.TestRepository;
import net.onlinetesting.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    TestRepository testRepository;


    @Override
    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    @Override
    public Test getById(long id) {
        return testRepository.getOne(id);
    }

    @Override
    public int calculatePoints(List<QuestionDTO> questionDTOS) {
        int totalPoints = 0;

        for (QuestionDTO question : questionDTOS){
            boolean isUserCorrectAnswered = true;
            for (AnswerDTO answer :question.getAnswers()) {
                if(answer.isAnswered() != answer.isCorrect()){
                    isUserCorrectAnswered = false;
                    break;
                }
            }
            if (isUserCorrectAnswered)
                totalPoints += 1;
        }

        return totalPoints;
    }

    @Override
    public void save(Test test) {
        testRepository.save(test);
    }
}
