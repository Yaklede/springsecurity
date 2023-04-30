package com.spring.security.domain.member.mapper

import com.spring.security.domain.member.entity.Member
import com.spring.security.domain.member.entity.dto.MemberDto

object MemberMapper {
    fun toEntity(dto: MemberDto): Member {
        return Member(
            id = dto.id,
            username = dto.username,
            loginId = dto.loginId,
            password = dto.password,
            email = dto.email,
            age = dto.age,
            roles = dto.roles
        )
    }

    fun toDto(entity: Member): MemberDto {
        return MemberDto(
            id = entity.id,
            username = entity.username,
            loginId = entity.loginId,
            password = entity.password,
            email = entity.email,
            age = entity.age,
            roles = entity.roles
        )
    }
}