package com.spring.security.domain.member.mapper

import com.spring.security.domain.member.entity.Member
import com.spring.security.domain.member.entity.dto.MemberDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MemberMapperTest {

    @Test
    fun toEntity() {
        //given
        val memberDto = MemberDto.mock()

        //when
        val member = MemberMapper.toEntity(memberDto)

        //then
        assertThat(member).isInstanceOf(Member::class.java)
        assertThat(member.id).isEqualTo(memberDto.id)
        assertThat(member.username).isEqualTo(memberDto.username)
        assertThat(member.age).isEqualTo(memberDto.age)
        assertThat(member.loginId).isEqualTo(memberDto.loginId)
        assertThat(member.password).isEqualTo(memberDto.password)
        assertThat(member.email).isEqualTo(memberDto.email)
    }

    @Test
    fun toDto() {
        //given
        val member = Member.mock()

        //when
        val memberDto = MemberMapper.toDto(member)

        //then
        assertThat(memberDto).isInstanceOf(MemberDto::class.java)
        assertThat(member.id).isEqualTo(memberDto.id)
        assertThat(member.username).isEqualTo(memberDto.username)
        assertThat(member.age).isEqualTo(memberDto.age)
        assertThat(member.loginId).isEqualTo(memberDto.loginId)
        assertThat(member.password).isEqualTo(memberDto.password)
        assertThat(member.email).isEqualTo(memberDto.email)
    }
}