package com.ferizoozoo.webQuizEngine.Quiz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer>{
	Page<Quiz> findAll(Pageable pageable);
}

