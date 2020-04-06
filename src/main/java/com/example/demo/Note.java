package com.example.demo;

import org.springframework.data.annotation.Id;

public class Note {
    @Id
    public String id;

    public String userId;
    public String title;
    public String text;

    public Note(String userId, String title, String text) {
        this.userId = userId;
        this.title = title;
        this.text = text;
    }
}
