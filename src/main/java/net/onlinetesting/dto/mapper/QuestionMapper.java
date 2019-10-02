package net.onlinetesting.dto.mapper;

import net.onlinetesting.dto.QuestionDTO;
import net.onlinetesting.model.Question;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring", uses = AnswerMapper.class)
public interface QuestionMapper {

    @Mapping(target = "test", ignore = true)
    Question toQuestion(QuestionDTO customerDto);

    @InheritInverseConfiguration
    QuestionDTO fromQuestion(Question customer);

    List<Question> toQuestions(List<QuestionDTO> questions);

    List<QuestionDTO> fromQuestions(List<Question> questions);
}
