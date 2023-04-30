package com.spring.security.provider

import com.spring.security.domain.member.entity.MemberDetails
import com.spring.security.domain.member.service.MemberDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationProvider: AuthenticationProvider {

    @Autowired
    lateinit var memberDetailsService: MemberDetailsService
    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    override fun authenticate(authentication: Authentication?): Authentication {
        //password 정보를 가져옴
        val password = authentication?.credentials as? String ?: throw BadCredentialsException("비밀번호 입력은 필수 입니다.")

        //저장된 회원정보를 조회
        val loadUserByUsername = memberDetailsService.loadUserByUsername(authentication.name)
        val member = loadUserByUsername as MemberDetails

        //패스워드 검증
        if (!passwordEncoder.matches(password, member.password)) throw BadCredentialsException("패스워드가 일치하지 않습니다.")

        //로그인 성공시 token 발급
        return UsernamePasswordAuthenticationToken(authentication.principal, null, authentication.authorities)
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication == UsernamePasswordAuthenticationToken::class.java
    }
}