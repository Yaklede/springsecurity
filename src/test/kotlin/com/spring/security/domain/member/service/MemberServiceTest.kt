package com.spring.security.domain.member.service

import com.spring.security.domain.member.entity.MemberRole
import com.spring.security.domain.member.entity.dto.MemberDto
import com.spring.security.domain.member.repository.MemberRepository
import com.spring.security.infoLog
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class MemberServiceTest @Autowired constructor(
    private val memberRepository: MemberRepository,
    private val memberService: MemberService,
    private val passwordEncoder: PasswordEncoder
) {
    @Test
    @DisplayName("비밀번호 암호화 테스트")
    fun passwordEncoder() {
        val password = "testPass"
        val encodePassword = passwordEncoder.encode(password)

        infoLog { encodePassword }

        assertThat(passwordEncoder.matches(password,encodePassword)).isTrue
    }

    @Test
    @DisplayName("회원가입 테스트")
    fun join() {
        //given
        val password = "password"
        val memberDto = MemberDto(
            username = "user",
            loginId = "loginId",
            password = password,
            email = "email",
            age =   10,
            roles = mutableListOf(MemberRole(role = "ROLE_USER"))
        )
        //when
        val joinMember = memberService.join(memberDto)
        //then
        val findMember = memberRepository.findById(joinMember.id!!)
        //생성된 아이디는 같아야 한다
        assertThat(joinMember.id).isEqualTo(findMember.get().id)

        //회원가입 후 리턴값은 memberDto를 반환해야 한다
        assertThat(joinMember).isInstanceOf(MemberDto::class.java)

        //회원가입된 객체의 password는 암호화 되어 있어야 한다
        assertThat(password).isNotEqualTo(joinMember.password)
        assertThat(passwordEncoder.matches(password, joinMember.password)).isTrue
    }

    @Test
    @DisplayName("회원을 찾지 못했을 시 에러 발생")
    fun memberNotFound() {
        //given
        val member = memberService.join(MemberDto.mock())

        //when //then
        assertThatThrownBy { memberService.findMemberById(member.id!! + 1) }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    @DisplayName("회원을 찾기 성공")
    fun memberFindSuccess() {
        //given
        val member = memberService.join(MemberDto.mock())

        //when
        val findMember = memberService.findMemberById(member.id!!)

        //then
        assertThat(member).isEqualTo(findMember)
        assertThat(member.id).isEqualTo(findMember.id)
    }
}