package com.example.demo;

import org.springframework.data.annotation.Id;

public class Group {
    @Id
    public String id;

    public String userId;
    public String participants;

    public Group(String userId, String participants) {
        this.userId = userId;
        this.participants = participants;
    }
}
