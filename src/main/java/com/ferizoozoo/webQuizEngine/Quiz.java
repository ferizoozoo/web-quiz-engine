package com.ferizoozoo.webQuizEngine;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Quiz {
    public int id;
    public String title;
    public String text;
    public String[] options;
    @JsonIgnore
    public int answer;

    Quiz(int id, String title, String text, String[] options, int answer) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public String getTitle() {
        return this.title;
    }

    public String getText() {
        return this.text;
    }

    public String[] getOptions() {
        return this.options;
    }

    public int getAnswer() {
        return this.answer;
    }
}

