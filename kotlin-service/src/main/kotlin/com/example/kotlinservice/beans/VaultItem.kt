package com.example.kotlinservice.beans

import com.fasterxml.jackson.annotation.JsonCreator
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
class VaultItem {
    @MongoId
    @Field
    @JsonProperty
    var id: String? = null

    @Field
    @field:JsonProperty
    lateinit var idCardNumber: String

    @Field
    @JsonProperty
    var resourceURI: String? = null

    @DocumentReference(collection = "User")
    @Field("ownerId")
    @JsonProperty(value = "ownerId")
    var owner: User? = null

    @Field
    @field:JsonProperty
    lateinit var itemName: String

    @Field
    @JsonProperty
    var creationDate: LocalDate? = null


    /*    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
        constructor(ownerId: String?) {
            owner!!.id = ownerId!!
        }*/

    constructor(
        id: String?,
        idCardNumber: String,
        resourceURI: String,
        ownerId: String,
        itemName: String,
        creationDate: LocalDate
    ) {
        this.id = id
        this.idCardNumber = idCardNumber
        this.resourceURI = resourceURI
        this.owner = User(ownerId)
        this.itemName = itemName
        this.creationDate = creationDate
    }
}