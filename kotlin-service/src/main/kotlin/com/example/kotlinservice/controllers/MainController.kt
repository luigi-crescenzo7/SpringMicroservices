package com.example.kotlinservice.controllers

import com.example.kotlinservice.beans.User
import com.example.kotlinservice.beans.VaultItem
import com.example.kotlinservice.repositories.UserRepository
import com.example.kotlinservice.repositories.VaultItemRepository
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

data class VaultItemDTO(val vaultItem: VaultItem, val ownerId: String)

@RestController
@Slf4j
@RequestMapping("/kotlin")
class MainController(
    private val repository: UserRepository,
    private val vaultItemRepository: VaultItemRepository
) {

    val log: Logger = getLogger<MainController>()


    @GetMapping("/owner/{id}")
    fun fetch(@PathVariable id: String): List<VaultItem> {
        log.info("Owner Id: $id")
        return vaultItemRepository.findAllByOwnerId(id)
    }

    @GetMapping("/users")
    fun users(): List<User> {
        log.info("/users endpoint")
        return repository.findAll()
    }

    @PostMapping("/save-item")
    fun item(@RequestBody item: VaultItemDTO): ResponseEntity<String> {
        log.info("Item received: $item")

        val savedItem = vaultItemRepository.save(
            VaultItem(
                resourceURI = item.vaultItem.resourceURI, idCardNumber = item.vaultItem.idCardNumber,
                itemName = item.vaultItem.itemName, creationDate = item.vaultItem.creationDate,
                ownerId = ObjectId(item.ownerId)
            )
        )


        return ResponseEntity<String>("${savedItem.id == null}", HttpStatus.OK)
    }

    @GetMapping("/items")
    fun items(): List<VaultItem> {
        log.info("/items endpoint")
        return vaultItemRepository.findAll()
    }

    @GetMapping("test")
    @ResponseBody
    fun test(): ResponseEntity<String> {
        log.info("/test endpoint")
        return ResponseEntity<String>("Hello\n", HttpStatus.OK)
    }
}