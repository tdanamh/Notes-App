package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ShortcutRepository extends MongoRepository<Shortcut, String> {
    public Shortcut findByUserId(String userId);
}
