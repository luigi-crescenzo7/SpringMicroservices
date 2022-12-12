package com.example.kotlinservice.controllers

import com.example.kotlinservice.beans.User
import com.example.kotlinservice.beans.VaultItem
import com.example.kotlinservice.repositories.UserRepository
import com.example.kotlinservice.repositories.VaultItemRepository
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody


inline fun <reified T> getLogger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}

@Controller
@Slf4j
@RequestMapping("/kotlin")
class MainController(
    private val repository: UserRepository,
    private val vaultItemRepository: VaultItemRepository
) {

    val log: Logger = getLogger<MainController>()

    @GetMapping("/users")
    @ResponseBody
    fun users(): MutableList<User> {
        log.info("/users endpoint")
        return repository.findAll()
    }

    @GetMapping("/items")
    @ResponseBody
    fun items(): MutableList<VaultItem> {
        log.info("/items endpoint")
        return vaultItemRepository.findAll()
    }

    @GetMapping(value = ["/test"])
    @ResponseBody
    fun test(): ResponseEntity<String> {
        log.info("/test endpoint")
        return ResponseEntity<String>("Hello", HttpStatus.OK)
    }
}