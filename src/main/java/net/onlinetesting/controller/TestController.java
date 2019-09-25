package net.onlinetesting.controller;

import net.onlinetesting.model.Test;
import net.onlinetesting.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping("/tests")
    public String indexPage(Model model) {

        List<Test> tests = testService.getAllTests();

        model.addAttribute("testList", tests);

        return "testsMenu";
    }

}
