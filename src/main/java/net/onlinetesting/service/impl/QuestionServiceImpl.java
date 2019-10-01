package net.onlinetesting.service.impl;

import net.onlinetesting.dto.QuestionDTO;
import net.onlinetesting.dto.mapper.QuestionMapper;
import net.onlinetesting.model.Question;
import net.onlinetesting.repository.QuestionRepository;
import net.onlinetesting.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.inject.Inject;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Inject
    QuestionMapper mapper;

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public List<Question> getTenRandomQuestions(long testId) {

        return questionRepository.getTenRandomQuestions(testId);

    }

    @Override
    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
    }

    @Override
    public Question getQuestion(long id) {
        return questionRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public List<QuestionDTO> toDtoQuestions(){
        return mapper.fromQuestions(questionRepository.findAll());
    }

    @Override
    public QuestionDTO getNextQuestion(long questionID, List<QuestionDTO> questionDTOS) {

        Optional<QuestionDTO> questionDTO = questionDTOS.stream()
                .filter(question -> question.getId() == questionID)
                .findAny();

        if(questionDTO.isPresent()) return questionDTO.get();
        else throw new java.lang.RuntimeException();
    }

}
