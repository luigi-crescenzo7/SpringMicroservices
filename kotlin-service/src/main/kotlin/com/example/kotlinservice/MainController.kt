package com.example.kotlinservice

import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody


inline fun <reified T> buildLogger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}


@Controller
@Slf4j
@RequestMapping("/kotlin")
class MainController {

    companion object {
        val log = buildLogger<MainController>()
    }


    @GetMapping(value = ["/test"])
    @ResponseBody
    fun test(): ResponseEntity<String> {
        log.info("/test resource")
        return ResponseEntity<String>("We", HttpStatus.OK)
    }
}