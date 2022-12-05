package com.example.demo.beans;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;

@Document("User")
@Data
public class User {

    @MongoId
    @Field
    private String id;
    @Field
    private String name;
    @Field
    private String surname;
    @Field
    private String email;
    @Field
    private String gender;
    @Field
    private String password;
    @Field
    private LocalDate dateOfBirth;
}
