package com.example.kotlinservice


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories
@Configuration
class KotlinServiceApplication

fun main(args: Array<String>) {
    runApplication<KotlinServiceApplication>(*args)
}
