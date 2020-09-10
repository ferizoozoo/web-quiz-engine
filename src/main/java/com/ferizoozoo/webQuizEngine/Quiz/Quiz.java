package com.ferizoozoo.webQuizEngine.Quiz;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String text;
    private String[] options;
    @JsonIgnore
    public int[] answer;

    public Quiz() {

    }

    public Quiz(String title, String text, String[] options, int[] answer) {
        this.setTitle(title);
        this.setText(text);
        this.setOptions(options);
        this.setAnswer(answer);
    }

    public int getId() { return this.id; }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) { this.title = title; }

    public String getText() {
        return this.text;
    }

    public void setText(String text) { this.text = text; }

    public String[] getOptions() {
        return this.options;
    }

    public void setOptions(String[] options) { this.options = options; }

    public int[] getAnswer() {
        return this.answer;
    }

    public void setAnswer(int[] answer) { this.answer = answer; }
}

