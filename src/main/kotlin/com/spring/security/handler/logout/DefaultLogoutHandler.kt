package com.spring.security.handler.logout

import com.spring.security.infoLog
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.logout.LogoutHandler

class DefaultLogoutHandler : LogoutHandler {
    override fun logout(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        //session 만료
        infoLog {
            "start logout handler"
        }
        request.session.let { it.invalidate() }
    }
}