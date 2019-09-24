package net.onlinetesting.controller;

import net.onlinetesting.model.Test;
import net.onlinetesting.model.TestRating;
import net.onlinetesting.model.User;
import net.onlinetesting.repository.TestRepository;
import net.onlinetesting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
public class DefaultController {

    @Autowired
    TestRepository testRepository;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/")
    public String indexPage(Model model) {

        return "index";
    }

}
