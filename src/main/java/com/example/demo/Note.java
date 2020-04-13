package com.example.demo;

import org.springframework.data.annotation.Id;

public class Note {
    @Id
    public String id;

    public String userId;
    public String title;
    public String text;
    public String category;
    public String dueDate;

    public Note(String userId, String title, String category, String text, String dueDate) {
        this.userId = userId;
        this.title = title;
        this.category = category;
        this.text = text;
        this.dueDate = dueDate;
    }
}
