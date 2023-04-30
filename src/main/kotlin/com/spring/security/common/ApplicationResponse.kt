package com.spring.security.common

data class ApplicationResponse<T> (
    val result: ApplicationResult,
    val payload: T
) {
    companion object {
        fun ok(payload: Any) = ApplicationResponse(
            result = ApplicationResult.ok("성공"),
            payload = payload
        )
    }
}