package com.spring.security.domain.member.repository

import com.spring.security.domain.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long>, CustomMemberRepository {
}