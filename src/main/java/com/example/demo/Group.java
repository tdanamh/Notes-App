package com.example.demo;

import org.springframework.data.annotation.Id;

public class Group {
    @Id
    public String id;

    public String name;
    public String userId;
    public String participants;

    public Group(String name, String userId, String participants) {
        this.name = name;
        this.userId = userId;
        this.participants = participants;
    }
}
