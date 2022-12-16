package com.example.kotlinservice


import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory
import org.springframework.data.mongodb.core.convert.MongoCustomConversions


@Configuration
class MongoConfig : AbstractReactiveMongoConfiguration() {

    @Value("\${spring.data.mongodb.uri}")
    lateinit var mongoDbUri: String

    @Bean
    override fun customConversions(): MongoCustomConversions {
        return super.customConversions()
    }

    @Bean
    fun mongoTemplate(): MongoTemplate {
        return MongoTemplate(SimpleMongoClientDatabaseFactory(mongoDbUri))
    }

    override fun getDatabaseName(): String {
        return "app-db"
    }
}