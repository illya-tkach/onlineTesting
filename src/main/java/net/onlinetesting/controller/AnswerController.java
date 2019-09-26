package net.onlinetesting.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@Controller
public class AnswerController {

    @PostMapping(value = "/checkboxAnswered", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity checkboxAnswered(@RequestBody List<String> answers) {

        answers.forEach(System.out::println);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @PostMapping(value = "/radioAnswered", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity radioAnswered(@RequestBody String answers) {

        System.out.println(answers);
        return ResponseEntity.ok(HttpStatus.OK);

    }

}
