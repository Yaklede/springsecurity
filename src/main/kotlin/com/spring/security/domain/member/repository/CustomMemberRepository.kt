package com.spring.security.domain.member.repository

import com.spring.security.domain.member.entity.Member

interface CustomMemberRepository {
    fun findByUsername(username: String): Member?
}