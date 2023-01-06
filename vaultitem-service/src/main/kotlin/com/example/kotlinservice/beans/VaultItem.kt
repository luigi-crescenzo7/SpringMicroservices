package com.example.kotlinservice.beans

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.mongodb.core.mapping.*
import java.time.LocalDate


@Document("VaultItem")
data class VaultItem(
    @MongoId(FieldType.OBJECT_ID)
    @Field
    @field:JsonProperty
    var id: String,
    @Field
    @field:JsonProperty var idCardNumber: String,
    @Field @field:JsonProperty var resourceURI: String,
    @DocumentReference(collection = "User") @Field(value = "ownerId") @field:JsonProperty(value = "ownerId")
    var user: User,
    @Field
    @field:JsonProperty var itemName: String,
    @Field @field:JsonProperty @field:JsonFormat(shape = JsonFormat.Shape.STRING) var creationDate: LocalDate
) {

    constructor(): this("", "", "", User(""), "", LocalDate.now())

}