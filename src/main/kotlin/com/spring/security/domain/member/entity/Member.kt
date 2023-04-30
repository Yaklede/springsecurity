package com.spring.security.domain

import com.spring.security.domain.dto.MemberDto
import jakarta.persistence.*

@Entity
@Table(name = "member")
data class Member (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var username: String? = null,
    var password: String? = null,
    var email: String? = null,
    var age: String? = null,
    var role: String? = null
) {
    private fun update(memberDto: MemberDto) {
        this.username = memberDto.username
        this.password = memberDto.password
        this.email = memberDto.email
        this.age = memberDto.age
        this.role = memberDto.role
    }
}
