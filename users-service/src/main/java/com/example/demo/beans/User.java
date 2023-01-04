package com.example.demo.beans;


import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String sex;
    @Field
    private String password;
    @Field
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDate dateOfBirth;
}
