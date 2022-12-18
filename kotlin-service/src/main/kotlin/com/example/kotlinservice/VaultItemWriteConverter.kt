package com.example.kotlinservice


import com.example.kotlinservice.beans.VaultItem
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.WritingConverter
import org.bson.Document
import org.bson.types.ObjectId



// non vi è necessità di un converter
/*
@WritingConverter
@Slf4j
class VaultItemWriteConverter : Converter<VaultItem, Document> {

    private val logger: Logger = LoggerFactory.getLogger(VaultItemWriteConverter::class.java)

    override fun convert(source: VaultItem): Document? {
        val document = Document()
        return with(document) {
            this.append("_id", ObjectId())
            this.append("idCardNumber", source.idCardNumber)
            this.append("resourceURI", source.resourceURI)
            this.append("ownerId", ObjectId(source.owner!!.id))
            this.append("creationDate", source.creationDate)
            this.append("itemName", source.itemName)
        }
    }
}*/