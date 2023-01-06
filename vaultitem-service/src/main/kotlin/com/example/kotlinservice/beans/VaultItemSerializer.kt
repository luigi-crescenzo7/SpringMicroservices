package com.example.kotlinservice.beans

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider


class VaultItemSerializer : JsonSerializer<VaultItem>() {
    override fun serialize(item: VaultItem?, jsonGenerator: JsonGenerator?, provider: SerializerProvider?) {
        jsonGenerator?.writeStartObject(item)
        jsonGenerator?.writeStringField("id", item?.id.toString())
        jsonGenerator?.writeStringField("itemName", item?.itemName)
        jsonGenerator?.writeStringField("idCardNumber", item?.idCardNumber)
        jsonGenerator?.writeStringField("resourceURI", item?.resourceURI)
        jsonGenerator?.writeStringField("ownerId", item?.user?.id)
        jsonGenerator?.writeStringField("creationDate", item?.creationDate.toString())
        jsonGenerator?.writeEndObject()
    }
}