package com.example.kotlinservice.beans

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.MongoId
import java.time.LocalDate

@Document("VaultItem")
data class VaultItem(
    @MongoId
    @Field
    var id: String? = null,
    @Field
    var resourceURI: String,
    @Field
    var idCardNumber: String,
    @Field
    var itemName: String,
    @Field
    var creationDate: LocalDate
)
