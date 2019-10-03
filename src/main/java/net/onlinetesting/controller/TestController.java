package net.onlinetesting.controller;

import net.onlinetesting.dto.QuestionDTO;
import net.onlinetesting.model.Test;
import net.onlinetesting.model.TestRating;
import net.onlinetesting.model.User;
import net.onlinetesting.service.TestRatingService;
import net.onlinetesting.service.TestService;
import net.onlinetesting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import java.security.Principal;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    TestService testService;

    @Autowired
    UserService userService;

    @Autowired
    TestRatingService testRatingService;

    @GetMapping("/tests")
    public String indexPage(Model model) {

        List<Test> tests = testService.getAllTests();

        model.addAttribute("testList", tests);

        return "testsMenu";
    }

    @GetMapping("/results")
    public String getResults(Model model, @SessionAttribute("questionList") List<QuestionDTO> questionList, @SessionAttribute("currentTestID") long testID) {

        int totalPoints = testService.calculatePoints(questionList);

        Principal principal = SecurityContextHolder.getContext().getAuthentication();

        User user = userService.findByEmail(principal.getName());
        Test test = testService.getById(testID);

        testRatingService.save(user, test, totalPoints);

        model.addAttribute("totalPoints", totalPoints);

        model.addAttribute("questionList", questionList);

        return "resultPage";
    }

    @GetMapping("/getRating")
    public String getRating(Model model) {

        List<TestRating> testRatings = testRatingService.findAll();

        return "resultPage";
    }

}
