package net.onlinetesting.controller;

import net.onlinetesting.model.User;
import net.onlinetesting.service.SecurityService;
import net.onlinetesting.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    SecurityService securityService;

    @GetMapping({"/registration","/new-user"})
    public String showRegistrationForm(Model model) {
        return AddDataToRegistForm(new User(), model,false);
    }

    @PostMapping({"/registration","/new-user"})
    public String getRegistration(@ModelAttribute("userForm") @Valid User userForm, Errors errors, Model model) {
        return getUserInfo(userForm, errors, model, false);
    }

    //Utility methods
    private String AddDataToRegistForm(User user, Model model,boolean edit) {
        model.addAttribute("userForm", user);
        model.addAttribute("listroles", userService.findAllRole());
        model.addAttribute("edit", edit);
        return "registration";
    }

    private String getUserInfo(User userForm, Errors errors, Model model,boolean edit) {
        if (errors.hasErrors()){
            return "registration";
        }

        String originalPassword = userForm.getPassword();
        try {
            userService.save(userForm);
        } catch (DataIntegrityViolationException e) {
            logger.info("Try to enter duplicate email [] to database", userForm.getLastName());
            return AddDataToRegistForm(userForm, model,false);
        }
        securityService.autologin(userForm.getEmail(), originalPassword);
        model.addAttribute("users", userForm);
        if (edit) return "redirect:/list_users";

        return "redirect:/";
    }
}
