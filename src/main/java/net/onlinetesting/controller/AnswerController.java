package net.onlinetesting.controller;

import net.onlinetesting.dto.QuestionDTO;
import net.onlinetesting.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @PostMapping(value = "/checkboxAnswered", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity checkboxAnswered(@RequestBody List<String> answers) {

        return ResponseEntity.ok(HttpStatus.OK);

    }

    @PostMapping(value = "/radioAnswered", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity radioAnswered(String answerID, String questionID, @SessionAttribute("questionList") List<QuestionDTO> questionList, HttpSession session) {

        try {
            answerService.setAnswerToQuestion(Long.parseLong(questionID), Long.parseLong(answerID), questionList);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }

    }

}
