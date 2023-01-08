package it.unisa.vaultservice.vaultitemservice.beans

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.*
import java.time.LocalDate


@Document("VaultItem")
@JsonSerialize(using = VaultItemSerializer::class)
data class VaultItem(
    @MongoId
    @Field
    @field:JsonProperty
    @field:JsonFormat(shape = JsonFormat.Shape.STRING)
    var id: ObjectId?,
    @Field
    @field:JsonProperty var idCardNumber: String,
    @Field @field:JsonProperty var resourceURI: String,
    @DocumentReference(collection = "User") @Field(value = "ownerId") @field:JsonProperty(value = "ownerId")
    var user: User,
    @Field
    @field:JsonProperty var itemName: String,
    @Field @field:JsonProperty @field:JsonFormat(shape = JsonFormat.Shape.STRING) var creationDate: LocalDate
) {

    constructor(): this(null, "", "", User(""), "", LocalDate.now())

}