package com.ferizoozoo.webQuizEngine.Quiz;

import engine.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    Page<Quiz> getQuizzes(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<Quiz> pagedResult = quizRepository.findAll(paging);
        return pagedResult;
    }


    Quiz getQuiz(int id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No quiz with id: " + id
                ));
    }


    Quiz addQuiz(QuizRequest quizRequest) {
        Quiz quiz = new Quiz(
                quizRequest.title,
                quizRequest.text,
                quizRequest.options,
                quizRequest.answer
        );
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        quiz.setUser(user);
        quizRepository.save(quiz);
        return quiz;
    }


    Answer solveQuiz(int id, AnswerRequest answer) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No quiz with id: " + id
                ));
        Answer answerResponse = new Answer();
        if (Arrays.equals(answer.answer, quiz.getAnswer())) {
            answerResponse.success = true;
            answerResponse.feedback = "Congratulations, you're right!";
        } else {
            answerResponse.success = false;
            answerResponse.feedback = "Wrong answer! Please, try again.";
        }
        return answerResponse;
    }


    ResponseEntity<?> deleteQuiz(int id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No quiz with id: " + id
                ));
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user found.");
        }
        if (quiz.getUser().getId() != user.getId()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        quizRepository.delete(quiz);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

