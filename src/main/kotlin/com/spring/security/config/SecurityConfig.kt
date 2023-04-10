package com.spring.security.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler


@Configuration
class SecurityConfig {
    companion object {
        class SuccessDefaultHandler() : AuthenticationSuccessHandler {
            override fun onAuthenticationSuccess(
                request: HttpServletRequest,
                response: HttpServletResponse,
                authentication: Authentication
            ) {
                println("authentication ${authentication.name}")
                response.sendRedirect("/")
            }
        }

        class FailDefaultHandler() : AuthenticationFailureHandler {
            override fun onAuthenticationFailure(
                request: HttpServletRequest,
                response: HttpServletResponse,
                exception: AuthenticationException
            ) {
                println("authentication Fail")
                response.sendRedirect("/login")
            }
        }
    }
    @Bean
    fun configurer(http: HttpSecurity): SecurityFilterChain {
        //인가 정책
        http
            .authorizeHttpRequests()
            .anyRequest().authenticated()

        //인증 정책 default form 인증
        http
            .formLogin()
            .defaultSuccessUrl("/")
            .failureForwardUrl("/login")
            .usernameParameter("username")
            .passwordParameter("password")
            .loginProcessingUrl("/login_proc")
            .successHandler(SuccessDefaultHandler())
            .failureHandler(FailDefaultHandler())
            .permitAll() //인증이 되어 있지 않아도 접근이 가능함

        return http.orBuild
    }
}