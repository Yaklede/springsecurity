package com.spring.security.controller

import com.spring.security.domain.member.entity.MemberRole
import com.spring.security.domain.member.entity.dto.MemberDto
import com.spring.security.domain.member.service.MemberService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SecurityController(
    private val memberService: MemberService
) {
    @GetMapping("/")
    fun index(): String {
        return "home"
    }

    @GetMapping("/join")
    fun join(): MemberDto {
        val member = MemberDto.mock().apply {
            this.username = "user"
            this.loginId = "loginId"
            this.password = "pass"
            this.roles = mutableListOf(MemberRole(role = "ADMIN"))
        }
        return memberService.join(member)
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