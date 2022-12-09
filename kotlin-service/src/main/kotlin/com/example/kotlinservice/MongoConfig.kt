package com.example.kotlinservice


import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory




@Configuration
class MongoConfig {

    @Value("\${spring.data.mongodb.uri}")
    lateinit var mongoDbUri: String

    @Bean
    fun mongoTemplate(): MongoTemplate {
        return MongoTemplate(SimpleMongoClientDatabaseFactory(mongoDbUri))
    }
}