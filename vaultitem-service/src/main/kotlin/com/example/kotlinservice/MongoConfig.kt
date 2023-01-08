package com.example.kotlinservice


import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory
import org.springframework.data.mongodb.core.convert.MongoCustomConversions



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

    @Bean
    fun mongoDbFactory(): MongoDatabaseFactory {
        return SimpleMongoClientDatabaseFactory(mongoClient(), getDatabaseName())
    }

    @Bean
    fun getDatabaseName(): String {
        return "app-db"
    }
}