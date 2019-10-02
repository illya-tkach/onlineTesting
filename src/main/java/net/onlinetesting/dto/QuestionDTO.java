package net.onlinetesting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.onlinetesting.model.QuestionType;
import net.onlinetesting.model.Test;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {

    private Long id;

    private String definition;

    private QuestionType questionType;

    private List<AnswerDTO> answers;

}
