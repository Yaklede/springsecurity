package com.spring.security

import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun <T : Any> T.logger(): Logger =
    LoggerFactory.getLogger(this.javaClass)


inline fun <T : Any> T.traceLog(
    message: (T) -> String
): T {
    logger().trace(message(this))
    return this
}

inline fun <T : Any> T.debugLog(
    message: (T) -> String
): T {
    logger().debug(message(this))
    return this
}

inline fun <T : Any> T.infoLog(
    message: (T) -> String
): T {
    logger().info(message(this))
    return this
}

inline fun <T : Any> T.warnLog(
    message: (T) -> String
): T {
    logger().warn(message(this))
    return this
}

inline fun <T : Any> T.errorLog(
    message: (T) -> String
): T {
    logger().error(message(this))
    return this
}