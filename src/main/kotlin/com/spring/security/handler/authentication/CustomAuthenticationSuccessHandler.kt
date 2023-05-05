package com.spring.security.handler.authentication

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.DefaultRedirectStrategy
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.security.web.savedrequest.HttpSessionRequestCache
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationSuccessHandler : SimpleUrlAuthenticationSuccessHandler() {

    //사용자가 인증전에 접근했던 request Cahch를 들고옴
    private val requestCache = HttpSessionRequestCache()
    private val redirectStrategy = DefaultRedirectStrategy()
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        defaultTargetUrl = "/"
        //이전에 접근 했던 reqeust를 생성
        val saveRequest = requestCache.getRequest(request, response)

        //이전에 접근했던 url이 있다면 해당 url로 이동 , 없다면 defaultTargetUrl로 이동 = /
        saveRequest?.redirectUrl?.let {
            redirectStrategy.sendRedirect(request, response, it)
        } ?: redirectStrategy.sendRedirect(request, response, defaultTargetUrl)
    }
}