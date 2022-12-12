package com.example.kotlinservice.beans

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.MongoId
import java.time.LocalDate


@Document("User")
data class User(
    @MongoId @Field
    private var id: String,
    @Field
    private var name: String,
    @Field
    private var surname: String,
    @Field
    private var email: String,
    @Field
    private var password: String,
    @Field
    private var gender: String,
    @Field
    private var dateOfBirth: LocalDate
)
