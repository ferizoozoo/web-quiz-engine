package com.ferizoozoo.webQuizEngine.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@Valid @RequestBody User newUser) {
        if (userRepository.findByEmail(newUser.getEmail()) == null) {
            userRepository.save(new User(newUser.getEmail(), encoder().encode(newUser.getPassword())));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}

