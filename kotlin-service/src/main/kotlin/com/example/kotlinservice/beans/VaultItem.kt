package com.example.kotlinservice.beans

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.MongoId
import java.time.LocalDate

@Document("VaultItem")
data class VaultItem(
    @MongoId
    @Field
    private var id: String,
    @Field
    private var resourceURI: String,
    @Field
    private var idCardNumber: String,
    @Field
    private var itemName: String,
    @Field
    private var creationDate: LocalDate
)
