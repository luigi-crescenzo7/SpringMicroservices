package com.example.kotlinservice.controllers

import com.example.kotlinservice.beans.User
import com.example.kotlinservice.beans.VaultItemDTO
import com.example.kotlinservice.beans.VaultItem
import com.example.kotlinservice.repositories.UserRepository
import com.example.kotlinservice.repositories.VaultItemRepository
import com.fasterxml.jackson.databind.ObjectMapper
import lombok.extern.slf4j.Slf4j
import org.bson.types.ObjectId
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


inline fun <reified T> getLogger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}


@RestController
@Slf4j
@RequestMapping("/kotlin")
class MainController(
    private val repository: UserRepository,
    private val vaultItemRepository: VaultItemRepository,
    private val mapper: ObjectMapper
) {

    val log: Logger = getLogger<MainController>()

    @PostMapping("/ownerId")
    fun fetch(@RequestBody ownerId: String): ResponseEntity<List<VaultItem>> {
        val items = vaultItemRepository.findAllByOwnerId(ObjectId(ownerId))
        return ResponseEntity(items, HttpStatus.OK)
    }

    @GetMapping("/users")
    fun users(): List<User> {
        log.info("/users endpoint")
        return repository.findAll()
    }

    @PostMapping("/save-item")
    fun item(@RequestBody item: VaultItemDTO): ResponseEntity<String> {
        log.info("item: $item")
        val user = User(item.ownerId)

        val savedItem = vaultItemRepository.insert(
            VaultItem(
                ObjectId().toString(),
                item.idCardNumber,
                item.resourceURI,
                user,
                item.itemName,
                item.creationDate
            )
        )

        return ResponseEntity<String>("${savedItem.id != null}", HttpStatus.OK)
    }

    @GetMapping("/items")
    fun items(): List<VaultItem> {
        log.info("/items endpoint")
        return vaultItemRepository.findAll()
    }

    @GetMapping("/test")
    @ResponseBody
    fun test(): ResponseEntity<String> {
        log.info("/test endpoint")
        return ResponseEntity<String>("Hello\n", HttpStatus.OK)
    }
}