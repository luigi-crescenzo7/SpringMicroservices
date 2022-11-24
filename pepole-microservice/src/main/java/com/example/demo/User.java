package com.example.demo;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("User")
@Data
public class User {

    @MongoId
    private String id;
    private String name;
}
