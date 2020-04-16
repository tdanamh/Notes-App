package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GroupRepository extends MongoRepository<Group, String> {
    public List<Group> findByUserId(String userId);
    public List<Group> findByUserIdAndName(String userId, String name); // List of groups for specific user with specific name
}
