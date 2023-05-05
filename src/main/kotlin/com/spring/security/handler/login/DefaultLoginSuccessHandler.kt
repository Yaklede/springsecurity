package com.spring.security.handler.login

import com.spring.security.domain.member.entity.MemberDetails
import com.spring.security.infoLog
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

class DefaultLoginSuccessHandler : AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        infoLog {
            "authentication ${authentication.name}"
        }
        val memberDetails = SecurityContextHolder.getContext().authentication.principal as MemberDetails
        memberDetails.authorities.map {
            println(it.authority)
        }
        response.sendRedirect("/")
    }
}