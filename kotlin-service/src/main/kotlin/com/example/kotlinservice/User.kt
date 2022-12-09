package com.example.kotlinservice

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.MongoId
import java.time.LocalDate


@Document("User")
data class User(
    @MongoId @Field
    val id: String,
    @Field
    val name: String,
    @Field
    val surname: String,
    @Field
    val email: String,
    @Field
    val password: String,
    @Field
    val gender: String,
    @Field
    val dateOfBirth: LocalDate
)
