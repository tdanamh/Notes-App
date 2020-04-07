package com.example.demo;

import org.springframework.data.annotation.Id;

public class Shortcut {
    @Id
    public String id;

    public String userId;
    public boolean nToNotes;
    public boolean pToProfile;

    public Shortcut(String userId, boolean nToNotes,  boolean pToProfile) {
        this.userId = userId;
        this.nToNotes = nToNotes;
        this.pToProfile = pToProfile;
    }
}