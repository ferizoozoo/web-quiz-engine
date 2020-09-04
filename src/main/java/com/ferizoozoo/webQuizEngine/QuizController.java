package com.ferizoozoo.webQuizEngine;

import org.springframework.web.bind.annotation.*;

@RestController
public class QuizController {

    @GetMapping("/api/quiz")
    public Quiz getQuiz() {
       Quiz quiz = new Quiz();
       quiz.title = "The Java Logo";
       quiz.text = "What is depicted on the Java logo?";
       quiz.options = new String[] {
               "Robot",
               "Tea leaf",
               "Cup of coffee",
               "Bug"
       };
       return quiz;
    }

    @PostMapping(path = "/api/quiz")
    public @ResponseBody Answer setQuiz(int answer) {
        Answer answer1 = new Answer();
        if (answer == 2) {
            answer1.success = true;
            answer1.feedback = "Congratulations, you're right!";
        } else {
            answer1.success = false;
            answer1.feedback = "Wrong answer! Please, try again.";
        }
        return answer1;
    }
}

