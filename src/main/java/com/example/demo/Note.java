package com.example.demo;

import org.springframework.data.annotation.Id;

public class Note {
    @Id
    public String id;

    public String title;
    public String text;

    public Note(String title, String text) {
        this.title = title;
        this.text = text;
    }
}
