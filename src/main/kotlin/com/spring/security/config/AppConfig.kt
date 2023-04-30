package com.spring.security.config

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig(
    @PersistenceContext
    private val entityManager: EntityManager
) {
    @Bean
    fun querydslConfig(): JPAQueryFactory {
        return JPAQueryFactory(entityManager)
    }
}