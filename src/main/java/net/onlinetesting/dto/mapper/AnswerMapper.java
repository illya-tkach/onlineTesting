package net.onlinetesting.dto.mapper;

import net.onlinetesting.dto.AnswerDTO;
import net.onlinetesting.model.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface AnswerMapper {

    @Mapping(target = "question", ignore = true)
    Answer toAnswer(AnswerDTO answerDTO);

    @Mapping(target = "answered", ignore = true)
    AnswerDTO fromAnswer(Answer answer);

}
