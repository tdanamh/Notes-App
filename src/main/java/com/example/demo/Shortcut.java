package com.example.demo;

import org.springframework.data.annotation.Id;

public class Shortcut {
    @Id
    public String id;

    public String userId;
    public boolean notesShortcut;
    public boolean profileShortcut;

    public Shortcut(String userId, boolean notesShortcut,  boolean profileShortcut) {
        this.userId = userId;
        this.notesShortcut = notesShortcut;
        this.profileShortcut = profileShortcut;
    }
}