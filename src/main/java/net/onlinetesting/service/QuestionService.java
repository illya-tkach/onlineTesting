package net.onlinetesting.service;

import net.onlinetesting.model.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getTenRandomQuestions (long testId);
    List<Question> getAllQuestions ();
}
