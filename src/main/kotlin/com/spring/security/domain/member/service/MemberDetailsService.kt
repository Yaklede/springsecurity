package com.spring.security.domain.member.service

import com.spring.security.domain.member.MemberDetails
import com.spring.security.domain.member.repository.MemberRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val memberRepository: MemberRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return memberRepository.findByUsername(username)?.let {
            MemberDetails.from(it)
        } ?: throw UsernameNotFoundException("회원을 찾을 수 없습니다.")
    }
}