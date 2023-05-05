package com.spring.security.domain.member.repository

import com.spring.security.domain.member.entity.Member
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class CustomMemberRepositoryImplTest @Autowired constructor(
    private val memberRepository: MemberRepository
) {
    @Test
    fun joinTest() {
        //given
        val member = Member.mock()
        assertThat(member.id).isEqualTo(999L)
    }
}