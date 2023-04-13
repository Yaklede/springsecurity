package com.spring.security.config

import com.spring.security.handler.login.DefaultLoginFailHandler
import com.spring.security.handler.login.DefaultLoginSuccessHandler
import com.spring.security.handler.logout.DefaultLogoutHandler
import com.spring.security.handler.logout.DefaultLogoutSuccessHandler
import com.spring.security.infoLog
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer.UserDetailsBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.logout.LogoutHandler
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler


@Configuration
class SecurityConfig {

    @Bean
    fun userDetailsService(): UserDetailsService {
        val userDetails =
        val users = User.withUserDetails()
    }

    @Bean
    fun configurer(manager: AuthenticationManagerBuilder) {
        manager.inMemoryAuthentication().withUser("user").password("{noop}1111").roles("USER")
        manager.inMemoryAuthentication().withUser("sys").password("{noop}1111").roles("SYS", "USER")
        manager.inMemoryAuthentication().withUser("admin").password("{noop}1111").roles("ADMIN", "SYS", "USER")
    }

    @Bean
    fun configurer(http: HttpSecurity): SecurityFilterChain {
        //인가 정책
        http
            .authorizeHttpRequests()
            .anyRequest()
            .authenticated()

        //권한 설정
        http
            .authorizeHttpRequests()
            .requestMatchers("/user").hasRole("USER")
            .requestMatchers("/sys").hasRole("SYS")
            .requestMatchers("/admin").hasRole("ADMIN")

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
}