package com.spring.security.domain.member.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import com.spring.security.domain.member.entity.Member
import com.spring.security.domain.member.entity.QMember
import com.spring.security.domain.member.entity.QMemberRole
import org.springframework.stereotype.Repository

@Repository
class CustomMemberRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
): CustomMemberRepository {
    override fun findByUsername(username: String): Member? {
        return jpaQueryFactory.selectFrom(QMember.member)
            .join(QMemberRole.memberRole)
            .fetchJoin()
            .where(QMember.member.username.eq(username))
            .fetchOne()
    }
}