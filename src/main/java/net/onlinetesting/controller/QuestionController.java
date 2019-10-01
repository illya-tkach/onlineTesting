package net.onlinetesting.controller;

import net.onlinetesting.dto.QuestionDTO;
import net.onlinetesting.dto.mapper.QuestionMapper;
import net.onlinetesting.model.Answer;
import net.onlinetesting.model.Question;
import net.onlinetesting.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@SessionAttributes("questionList")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @ModelAttribute("questionList")
    public  List<QuestionDTO> setUpUserForm() {
        return new ArrayList<>();
    }

    @GetMapping("/getRandQuestions-{testId}")
    public String getQuestions(@PathVariable("testId") long testId, Model model, @ModelAttribute("questionList") List<QuestionDTO> questionList)  {

        if (questionList.size() == 0){
           List<QuestionDTO> questionDTOList = questionService.toDtoQuestions();
           model.addAttribute("questionList", questionDTOList);
        }

        return "test";
    }

    @RequestMapping(value = "/getQuestion-{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Question setServedStatus(@PathVariable("id") long id, HttpSession httpSession)  {
        Question question = questionService.getQuestion(id);
        return question;
    }

}
