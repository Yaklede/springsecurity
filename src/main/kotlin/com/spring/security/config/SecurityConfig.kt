package com.spring.security.config

import com.spring.security.handler.login.DefaultLoginFailHandler
import com.spring.security.handler.login.DefaultLoginSuccessHandler
import com.spring.security.handler.logout.DefaultLogoutHandler
import com.spring.security.handler.logout.DefaultLogoutSuccessHandler
import com.spring.security.provider.CustomAuthenticationProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain



@Configuration
@EnableWebSecurity
class SecurityConfig{
    @Bean
    fun authManager(http: HttpSecurity): AuthenticationManager {
        val provider = http.getSharedObject(AuthenticationManagerBuilder::class.java)
        provider.authenticationProvider(CustomAuthenticationProvider())
        return provider.orBuild
    }

    @Bean
    fun configurer(http: HttpSecurity): SecurityFilterChain {
        //권한 설정
        http
            .authorizeHttpRequests()
            .requestMatchers("/user").hasRole("ROLE_USER")
            .requestMatchers("/sys").hasRole("ROLE_SYS")
            .requestMatchers("/admin").hasRole("ROLE_ADMIN")

        //인가 정책
        http
            .authorizeHttpRequests()
            .requestMatchers("/join")
            .permitAll()

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
            .successHandler(DefaultLoginSuccessHandler())
            .failureHandler(DefaultLoginFailHandler())
            .permitAll() //인증이 되어 있지 않아도 접근이 가능함


        //logout
        http
            .logout() // logout
            .logoutUrl("/logout") //logout request url
            .logoutSuccessUrl("/login") // logout Success Redirect Url
            .addLogoutHandler(DefaultLogoutHandler()) // 로그아웃 요청시 동작하는 handler
            .logoutSuccessHandler(DefaultLogoutSuccessHandler()) //로그아웃 성공시 동작하는 handler
            .deleteCookies("remember-me") //해당 이름의 쿠키 삭제

        //session Management Filter
        http
            .sessionManagement()
            .maximumSessions(1) // 최대 세션 1개
            .maxSessionsPreventsLogin(true) // 1개 이후에는 더이상 세션이 생기지 않도록 즉 2번째 요청부터 로그인을 막음 false일 땐 기존 사용자 로그아웃

        return http.orBuild
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(16)
    }
}