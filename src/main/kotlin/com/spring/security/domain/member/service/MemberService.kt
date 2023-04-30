package com.spring.security.domain.member.service

import com.spring.security.domain.member.entity.dto.MemberDto
import com.spring.security.domain.member.mapper.MemberMapper
import com.spring.security.domain.member.repository.MemberRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    @Transactional
    fun join(memberDto: MemberDto): MemberDto {
        memberDto.password?.let {
            memberDto.password = passwordEncoder.encode(it)
        }
        val member = MemberMapper.toEntity(memberDto)
        memberRepository.save(member).let {
            return MemberMapper.toDto(it)
        }
    }

    fun findMemberById(memberId: Long): MemberDto {
        memberRepository.findById(memberId).orElseThrow { throw IllegalArgumentException("회원이 존재하지 않습니다.") }.let {
            return MemberMapper.toDto(it)
        }
    }
}