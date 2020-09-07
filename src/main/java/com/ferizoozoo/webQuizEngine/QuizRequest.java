package com.ferizoozoo.webQuizEngine;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class QuizRequest {
    @NotNull
    @NotBlank
    public String title;
    @NotNull
    @NotBlank
    public String text;
    @Size(min = 2)
    @NotNull
    public String[] options;
    public int[] answer = new int[0];
}
