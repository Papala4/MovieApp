package com.space.network.exception

class BaseException(
    val code: ErrorCode,
    val httpStatus: Int? = null,
    message: String,
    cause: Throwable? = null
) : Exception(message, cause)