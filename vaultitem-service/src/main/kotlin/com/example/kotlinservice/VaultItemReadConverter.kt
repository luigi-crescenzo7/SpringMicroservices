package com.example.kotlinservice


import com.example.kotlinservice.beans.VaultItem
import org.springframework.core.convert.converter.Converter
import org.bson.Document
import org.springframework.data.convert.ReadingConverter
import org.springframework.stereotype.Component
import java.time.ZoneId

@Component
@ReadingConverter
class VaultItemReadConverter : Converter<Document, VaultItem> {
    override fun convert(source: Document): VaultItem {
        val item = VaultItem()
        return with(item) {
            this.id = source.getObjectId("_id")
            this.itemName = source.getString("itemName")
            this.user.id = source.getObjectId("ownerId").toString()
            this.resourceURI = source.getString("resourceURI")
            this.creationDate = source.getDate("creationDate").toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            this.idCardNumber = source.getString("idCardNumber")
            this
        }
    }
}