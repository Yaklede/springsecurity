package com.spring.security.domain.member.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors


class MemberDetails(
    private val username: String,
    private val password: String,
    private val roles: MutableList<MemberRole>
): UserDetails {
    companion object {
        fun from(member: Member): MemberDetails {
            return with(member) {
                MemberDetails(
                    username = username!!,
                    password = password!!,
                    roles = roles!!
                )
            }
        }
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return roles.stream().map { role -> SimpleGrantedAuthority("ROLE_$role") }.collect(Collectors.toSet())
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}
