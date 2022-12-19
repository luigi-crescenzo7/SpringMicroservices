package com.example.kotlinservice.beans

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.MongoId
import java.time.LocalDate


@Document("User")
data class User(
    @MongoId @Field
    var id: String,
    @Field
    var name: String,
    @Field
    var surname: String,
    @Field
    var email: String,
    @Field
    var password: String,
    @Field
    var gender: String,
    @Field
    var dateOfBirth: LocalDate
) {
    constructor(ownerId: String) : this(ownerId, "", "", "", "", "", LocalDate.now())
}
