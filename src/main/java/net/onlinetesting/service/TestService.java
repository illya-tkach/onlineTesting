package net.onlinetesting.service;

import net.onlinetesting.dto.QuestionDTO;
import net.onlinetesting.model.Test;

import java.util.List;

public interface TestService {
    List<Test> getAllTests();

    Test getById(long id);

    int calculatePoints(List<QuestionDTO> questionDTOS);

    void save(Test test);
}
