package com.example.demo;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;

@Document("User")
@Data
public class User {

    @MongoId
    private String id;
    private String name;
    private String surname;
    private String email;
    private String gender;
    private String password;
    private LocalDate dateOfBirth;
}
