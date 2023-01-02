package com.example.kotlinservice.beans

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Data
import lombok.NoArgsConstructor
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.MongoId
import java.time.LocalDate

@Data
@Document("VaultItem")
@NoArgsConstructor
class VaultItem(
    @MongoId
    @Field
    @field:JsonProperty var id: String?, @Field
    @field:JsonProperty var idCardNumber: String, resourceURI: String, ownerId: User, @Field
    @field:JsonProperty var itemName: String, creationDate: LocalDate
) {

    @Field
    @field:JsonProperty
    var resourceURI: String? = resourceURI

    @DocumentReference(collection = "User")
    @Field("ownerId")
    @field:JsonProperty(value = "ownerId")
    var ownerId: User? = ownerId

    @Field
    @field:JsonProperty
    @field:JsonFormat(shape = JsonFormat.Shape.STRING)
    var creationDate: LocalDate? = creationDate


}