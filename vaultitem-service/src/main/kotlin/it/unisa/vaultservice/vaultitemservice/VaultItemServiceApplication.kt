package it.unisa.vaultservice.vaultitemservice


import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import it.unisa.vaultservice.vaultitemservice.beans.VaultItem
import it.unisa.vaultservice.vaultitemservice.beans.VaultItemSerializer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories
class VaultItemServiceApplication {
    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper().registerModule(JavaTimeModule()).registerModule(SimpleModule()
                .addSerializer(VaultItem::class.javaObjectType, VaultItemSerializer()))
    }
}

fun main(args: Array<String>) {
    runApplication<VaultItemServiceApplication>(*args)
}
