package com.ferizoozoo.webQuizEngine.Quiz;

import engine.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping
    public @ResponseBody ResponseEntity<Page<Quiz>> getAllQuizzes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int pageSize
    ) {
        return new ResponseEntity<Page<Quiz>>(
                quizService.getQuizzes(page, pageSize),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/{id}")
    public @ResponseBody Quiz getQuiz(@PathVariable int id) {
         return quizService.getQuiz(id);
    }


    @PostMapping(consumes = "application/json")
    public @ResponseBody Quiz createQuiz(@Valid @RequestBody QuizRequest quizRequest) {
        return quizService.addQuiz(quizRequest);
    }

    @PostMapping(value = "/{id}/solve", consumes = "application/json")
    public @ResponseBody Answer solveQuiz(@PathVariable int id, @RequestBody AnswerRequest answer) {
        return quizService.solveQuiz(id, answer);
    }

    @DeleteMapping(value = "/{id}")
    public @ResponseBody ResponseEntity<?> deletePost(@PathVariable int id) {
        return quizService.deleteQuiz(id);
    }
}

