package com.spring.security.handler.authentication

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationFailureHandler : SimpleUrlAuthenticationFailureHandler() {
    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException
    ) {
        var errorMessage = "Invalid Username or Password"
        when (exception) {
            is UsernameNotFoundException -> errorMessage
        }
        setDefaultFailureUrl("/exception?exception=$errorMessage")

        super.onAuthenticationFailure(request, response, exception)
    }
}