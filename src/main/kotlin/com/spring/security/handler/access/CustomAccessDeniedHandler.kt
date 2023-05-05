package com.spring.security.handler.access

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component

/**
 * 로그인에 성공해서 인증은 성공 했으나 자원에 접근 권한이 없는 상태
 */
@Component
class CustomAccessDeniedHandler : AccessDeniedHandler {
    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        val deniedUrl = "/denied?exception=${accessDeniedException.message}"
        response.sendRedirect(deniedUrl)
    }
}