package com.ferizoozoo.webQuizEngine.Quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class QuizController {
    @Autowired
    private QuizRepository quizRepository;

    @GetMapping(value = "/quizzes")
    public @ResponseBody Collection<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    @GetMapping(value = "/quizzes/{id}")
    public @ResponseBody Quiz getQuiz(@PathVariable int id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No quiz with id: " + id
                ));
        return quiz;
    }

    @PostMapping(value = "/quizzes", consumes = "application/json")
    public @ResponseBody Quiz createQuiz(@Valid @RequestBody QuizRequest quizRequest) {
        Quiz quiz = new Quiz(
                quizRequest.title,
                quizRequest.text,
                quizRequest.options,
                quizRequest.answer
        );
        quizRepository.save(quiz);
        return quiz;
    }

    @PostMapping(value = "/quizzes/{id}/solve", consumes = "application/json")
    public @ResponseBody Answer solveQuiz(@PathVariable int id, @RequestBody AnswerRequest answer) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No quiz with id: " + id
                ));
        Answer answerResponse = new Answer();
        if (Arrays.equals(answer.answer, quiz.answer)) {
            answerResponse.success = true;
            answerResponse.feedback = "Congratulations, you're right!";
        } else {
            answerResponse.success = false;
            answerResponse.feedback = "Wrong answer! Please, try again.";
        }
        return answerResponse;
    }
}

