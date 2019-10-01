package net.onlinetesting.service;

import net.onlinetesting.dto.QuestionDTO;

import java.util.List;

public interface AnswerService {
    void setAnswerToQuestion (long questionID, long answerID, List<QuestionDTO> questionDTOList);
}
