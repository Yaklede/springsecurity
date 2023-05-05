package com.spring.security.controller

import com.spring.security.domain.member.entity.MemberRole
import com.spring.security.domain.member.entity.dto.MemberDto
import com.spring.security.domain.member.service.MemberService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

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

    @GetMapping("/logoutTest")
    fun logout(request: HttpServletRequest, response: HttpServletResponse) {

        val authentication = SecurityContextHolder.getContext().authentication

        if (authentication != null) {
            SecurityContextLogoutHandler().logout(request,response,authentication)
            request.session.invalidate()
        }
        response.sendRedirect("/login")
    }

    @GetMapping("/exception")
    fun error(
        @RequestParam("exception", required = false) exception: String
    ): ResponseEntity<String> {
        return ResponseEntity.ok(
            URLDecoder.decode(exception, StandardCharsets.UTF_8)
        )
    }

    @GetMapping("/denied")
    fun denied(
        @RequestParam("exception", required = false) exception: String
    ): ResponseEntity<String> {
        return ResponseEntity.ok(
            URLDecoder.decode(exception, StandardCharsets.UTF_8)
        )
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