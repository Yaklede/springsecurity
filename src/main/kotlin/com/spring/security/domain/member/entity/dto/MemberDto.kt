package com.spring.security.domain.member.entity.dto

import com.spring.security.domain.member.entity.MemberRole

data class MemberDto(
    var id: Long? = null,
    var username: String? = null,
    var loginId: String? = null,
    var password: String? = null,
    var email: String? = null,
    var age: Int? = null,
    var roles: MutableList<MemberRole>? = null,
) {
    companion object {
        fun mock() = MemberDto(
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
