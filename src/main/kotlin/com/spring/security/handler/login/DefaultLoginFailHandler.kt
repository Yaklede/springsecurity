package com.spring.security.handler.login

import com.spring.security.infoLog
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler

class DefaultLoginFailHandler : AuthenticationFailureHandler {

    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException
    ) {
        infoLog {
            "authentication Fail"
            "exception message ${exception.message}"
        }

        response.sendRedirect("/login")
    }
}