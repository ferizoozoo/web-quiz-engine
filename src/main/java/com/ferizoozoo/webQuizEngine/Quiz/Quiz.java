package com.ferizoozoo.webQuizEngine.Quiz;

import com.fasterxml.jackson.annotation.JsonIgnore;
import engine.User.User;

import javax.persistence.*;

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
    @ManyToOne
    @JoinColumn(name = "UserId")
    @JsonIgnore
    private User user;

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

    public User getUser() { return this.user; }

    public void setUser(User user) { this.user = user; }
}

