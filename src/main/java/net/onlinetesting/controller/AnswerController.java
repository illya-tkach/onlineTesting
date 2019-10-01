package net.onlinetesting.controller;

import net.onlinetesting.dto.QuestionDTO;
import net.onlinetesting.service.AnswerService;
import net.onlinetesting.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @Autowired
    QuestionService questionService;

    @PostMapping(value = "/checkboxAnswered", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity checkboxAnswered(@RequestBody List<String> answers) {

        return ResponseEntity.ok(HttpStatus.OK);

    }

    @RequestMapping(value = "/radioAnswered", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public QuestionDTO radioAnswered(String answerID, String questionID, String nextQuestionID, @SessionAttribute("questionList") List<QuestionDTO> questionList, HttpSession session) {

        answerService.setAnswerToQuestion(Long.parseLong(questionID), Long.parseLong(answerID), questionList);

        return questionService.getNextQuestion(Long.parseLong(nextQuestionID), questionList);

    }

}
