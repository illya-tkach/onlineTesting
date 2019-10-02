package net.onlinetesting.service.impl;

import net.onlinetesting.dto.AnswerDTO;
import net.onlinetesting.dto.QuestionDTO;
import net.onlinetesting.service.AnswerService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Override
    public void setAnswerToQuestion(long questionID, long answerID, List<QuestionDTO> questionDTOList) {

        Optional<List<AnswerDTO>> answerDTOSet = questionDTOList.stream()
                .filter(question -> question.getId() == questionID)
                .map(question -> question.getAnswers())
                .findAny();

        if(answerDTOSet.isPresent())
        {
            answerDTOSet.get().stream()
                    .filter(answer -> answer.getId() == answerID)
                    .forEach(answer -> answer.setAnswered(true));
        }
    }

}
