package com.example.kotlinservice


import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import org.springframework.data.mongodb.core.mapping.MongoMappingContext



@Configuration
class MongoConfig {

    @Value("\${spring.data.mongodb.uri}")
    lateinit var mongoDbUri: String

    @Bean
    fun mongoClient(): MongoClient {
        return MongoClients.create(mongoDbUri)
    }

    @Bean
    fun mongoCustomConversions() : MongoCustomConversions {
        return MongoCustomConversions(listOf(VaultItemReadConverter()))
    }


    fun configureConverters(converterConfigurationAdapter: MongoCustomConversions.MongoConverterConfigurationAdapter) {
        //converterConfigurationAdapter.registerConverter(VaultItemWriteConverter())
    }

    @Bean
    fun mongoDbFactory(): MongoDatabaseFactory {
        return SimpleMongoClientDatabaseFactory(mongoClient(), getDatabaseName())
    }

    @Bean
    fun getDatabaseName(): String {
        return "app-db"
    }

    fun mappingMongoConverter(): MappingMongoConverter {
        return MappingMongoConverter(DefaultDbRefResolver(mongoDbFactory()), MongoMappingContext())
    }
}