package it.unisa.vaultservice.vaultitemservice.controllers

import it.unisa.vaultservice.vaultitemservice.beans.User
import it.unisa.vaultservice.vaultitemservice.beans.VaultItemDTO
import it.unisa.vaultservice.vaultitemservice.beans.VaultItem
import it.unisa.vaultservice.vaultitemservice.repositories.VaultItemRepository
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


@Slf4j
@RestController
@RequestMapping("/vault")
class MainController(
    private val vaultItemRepository: VaultItemRepository,
    private val mapper: ObjectMapper
) {

    val log: Logger = getLogger<MainController>()

    @PostMapping("/ownerId")
    fun fetch(@RequestBody ownerId: String): ResponseEntity<List<VaultItem>> {
        val items = vaultItemRepository.findAllByOwnerId(ObjectId(ownerId))
        return ResponseEntity(items, HttpStatus.OK)
    }

    @PostMapping("/delete-item")
    fun delete(@RequestBody id: String): ResponseEntity<String> {
        val itemId = mapper.readTree(id).get("id").asText()
        val objId = ObjectId(itemId)
        val opt = vaultItemRepository.findById(objId)

        if(opt.isEmpty)
            return ResponseEntity<String>("item not found with id $itemId", HttpStatus.BAD_REQUEST)

        vaultItemRepository.deleteById(objId)
        return ResponseEntity<String>("true", HttpStatus.OK)
    }

    @PostMapping("/update-item")
    fun update(@RequestBody item: VaultItemDTO): ResponseEntity<String> {
        val objId = ObjectId(item.id)
        val opt = vaultItemRepository.findById(objId)

        if(opt.isEmpty)
            return ResponseEntity<String>("item not found with id ${item.id}", HttpStatus.BAD_REQUEST)

        val itemToUpdate = VaultItem(objId,
                                    item.idCardNumber,
                                    item.resourceURI,
                                    User(item.ownerId),
                                    item.itemName,
                                    item.creationDate)

        val updatedItem = vaultItemRepository.save(itemToUpdate)
        return ResponseEntity<String>("${updatedItem.id != null}", HttpStatus.OK)
    }
    @PostMapping("/save-item")
    fun item(@RequestBody item: VaultItemDTO): ResponseEntity<String> {
        val user = User(item.ownerId)

        val savedItem = vaultItemRepository.save(
            VaultItem(
                null,
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
        return vaultItemRepository.findAll()
    }

    @GetMapping("/test")
    fun test(): ResponseEntity<String> {
        return ResponseEntity<String>("Hello\n", HttpStatus.OK)
    }
}