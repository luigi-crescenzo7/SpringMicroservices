package com.example.kotlinservice.beans

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Data
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.MongoId
import java.time.LocalDate

@Data
@Document("VaultItem")
class VaultItem {
    @MongoId
    @Field
    @JsonProperty
    var id: String? = null

    @Field
    @JsonProperty
    var idCardNumber: String? = null

    @Field
    @JsonProperty
    var resourceURI: String? = null

    @DocumentReference(collection = "User")
    @Field("ownerId")
    @JsonProperty(value = "ownerId")
    var owner: User? = null

    @Field
    @JsonProperty
    var itemName: String? = null

    @Field
    @JsonProperty
    var creationDate: LocalDate? = null

    constructor()

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    constructor(ownerId: String?) {
        owner!!.id = ownerId!!
    }

    constructor(
        id: String?,
        idCardNumber: String?,
        resourceURI: String?,
        owner: User?,
        itemName: String?,
        creationDate: LocalDate?
    ) {
        this.id = id
        this.idCardNumber = idCardNumber
        this.resourceURI = resourceURI
        this.owner = owner
        this.itemName = itemName
        this.creationDate = creationDate
    }
}