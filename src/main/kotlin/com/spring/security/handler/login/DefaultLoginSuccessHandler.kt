package com.spring.security.handler.login

import com.spring.security.infoLog
import com.spring.security.logger
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
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
        response.sendRedirect("/")
    }
}