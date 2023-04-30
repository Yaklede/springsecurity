package com.spring.security.domain.member.entity

import com.spring.security.domain.member.entity.dto.MemberDto
import jakarta.persistence.*

@Entity
@Table(name = "member")
data class Member (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var username: String? = null,
    var loginId: String? = null,
    var password: String? = null,
    var email: String? = null,
    var age: Int? = null,
    @OneToMany(fetch = FetchType.EAGER ,cascade = [CascadeType.ALL])
    var roles: MutableList<MemberRole>? = null
) {
    fun update(memberDto: MemberDto) {
        this.username = memberDto.username
        this.loginId = memberDto.loginId
        this.password = memberDto.password
        this.email = memberDto.email
        this.age = memberDto.age
        this.roles = memberDto.roles
    }

    companion object {
        fun mock() = Member(
            id = 999L,
            username = "user",
            loginId = "loginId",
            password = "password",
            email = "email@google.com",
            age = 20,
            roles = mutableListOf(MemberRole(999L, "ROLE_USER"))
        )
    }
}
