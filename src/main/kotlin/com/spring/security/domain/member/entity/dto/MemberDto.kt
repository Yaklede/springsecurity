package com.spring.security.domain.dto

data class MemberDto(
    var username: String? = null,
    var password: String? = null,
    var email: String? = null,
    var age: String? = null,
    var role: String? = null,
)
