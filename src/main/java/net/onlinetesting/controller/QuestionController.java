package net.onlinetesting.controller;

import net.onlinetesting.model.Answer;
import net.onlinetesting.model.Question;
import net.onlinetesting.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

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
    public  List<Question> etUpUserForm() {
        return new ArrayList<>();
    }

    @GetMapping("/getRandQuestions-{testId}")
    public String setServedStatus(@PathVariable("testId") long testId, Model model, @ModelAttribute("questionList") List<Question> questionList)  {

        if (questionList.size() == 0){
//           questionList = questionService.getTenRandomQuestions(testId);
           List<Question> questionList2 = questionService.getAllQuestions();
           Set<Answer> answers = questionList2.get(0).getAnswers();
           model.addAttribute("questionList", questionList2);
        }

        return "test";
    }
}
