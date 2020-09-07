package com.ferizoozoo.webQuizEngine;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

@RestController
public class QuizController {
    private static int idGenerator = 1;
    private Map<Integer ,Quiz> quizzes = new HashMap<Integer, Quiz>();

    private void addQuiz(Quiz quiz) {
        quizzes.put(quiz.id, quiz);
    }

    @PostMapping(value = "/api/quizzes", consumes = "application/json")
    public @ResponseBody Quiz createQuiz(@Valid @RequestBody QuizRequest quizRequest) {
        Quiz quiz = new Quiz(
            idGenerator++,
            quizRequest.title,
            quizRequest.text,
            quizRequest.options,
            quizRequest.answer
        );
        addQuiz(quiz);
        return quiz;
    }

    @GetMapping(value = "/api/quizzes/{id}")
    public @ResponseBody Quiz getQuiz(@PathVariable int id) {
        if (quizzes.containsKey(id)) {
            return quizzes.get(id);
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found."
        );
    }

    @GetMapping(value = "/api/quizzes")
    public @ResponseBody Collection<Quiz> getAllQuizzes() {
        return quizzes.values();
    }

    @PostMapping(value = "/api/quizzes/{id}/solve", consumes = "application/json")
    public @ResponseBody Answer solveQuiz(@PathVariable int id, @RequestBody AnswerRequest answer) {
        if (!quizzes.containsKey(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found."
            );
        }
        var quiz = quizzes.get(id);
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

