package it.unisa.vaultservice.vaultitemservice.beans

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType
import org.springframework.data.mongodb.core.mapping.MongoId
import java.time.LocalDate


@Document("User")
data class User(
    @MongoId(FieldType.OBJECT_ID)
    @Field
    var id: String?,
    @Field
    var name: String?,
    @Field
    var surname: String?,
    @Field
    var email: String?,
    @Field
    var password: String?,
    @Field
    var sex: String?,
    @Field
    @field:JsonFormat(shape = JsonFormat.Shape.STRING)
    var dateOfBirth: LocalDate?
) {
    constructor(ownerId: String) : this(ownerId, null, null, null, null, null, null)
}
