package com.spring.security.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SecurityController {

    @GetMapping("/")
    fun index(): String {
        return "home"
    }

    @GetMapping("/loginPage")
    fun loginPage(): String {
        return "loginPage"
    }

    @GetMapping("/user")
    fun user(): String {
        return "user"
    }

    @GetMapping("/sys")
    fun sys(): String {
        return "sys"
    }

    @GetMapping("/admin")
    fun admin(): String {
        return "admin"
    }
}