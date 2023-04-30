package com.spring.security.common

import org.springframework.http.HttpStatus

data class ApplicationResult(
    val code: Int,
    val message: String
) {
    companion object {
        fun ok(message: String): ApplicationResult {
            return ApplicationResult(
                code = HttpStatus.OK.value(),
                message = message
            )
        }
        fun fail(message: String): ApplicationResult {
            return ApplicationResult(
                code = HttpStatus.BAD_REQUEST.value(),
                message = message
            )
        }
    }
}
