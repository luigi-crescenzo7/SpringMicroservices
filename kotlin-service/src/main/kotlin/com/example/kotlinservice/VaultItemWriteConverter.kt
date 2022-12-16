package com.example.kotlinservice

import com.example.kotlinservice.controllers.VaultItemDTO
import com.mongodb.BasicDBObjectBuilder
import com.mongodb.DBObject
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.WritingConverter


@WritingConverter
class VaultItemWriteConverter : Converter<VaultItemDTO, DBObject> {
    override fun convert(source: VaultItemDTO): DBObject {

        /*val dbo = BasicDBObject()

        with(source) {
            dbo.put("_id", this.vaultItem.id+"")
            dbo.put("itemName")
        }*/

        return BasicDBObjectBuilder.start()
            .add("_id", source.vaultItem.id!!)
            .add("itemName", source.vaultItem.itemName)
            .add("creationDate", source.vaultItem.creationDate)
            .add("resourceURI", source.vaultItem.resourceURI)
            .add("idCardNumber", source.vaultItem.idCardNumber)
            .add("ownerId", source.ownerId)
            .get()
    }
}