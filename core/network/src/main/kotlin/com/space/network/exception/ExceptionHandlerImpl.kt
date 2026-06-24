package com.space.network.exception

import retrofit2.HttpException
import java.io.IOException

class ExceptionHandlerImpl : ExceptionHandler {
    override suspend fun getExceptionByThrowable(throwable: Throwable): BaseException =
        when (throwable) {
            is IOException -> BaseException(
                code = ErrorCode.NETWORK,
                message = "Network error. Please check your connection",
                cause = throwable
            )
            is HttpException -> BaseException(
                code = ErrorCode.HTTP,
                httpStatus = throwable.code(),
                message = throwable.toReadableMessage()
            )
            else -> BaseException(
                code = ErrorCode.UNKNOWN,
                message = "Something went wrong. Please try again",
                cause = throwable
            )
        }

    private fun HttpException.toReadableMessage(): String = when (code()) {
        in 300..399 -> "Redirect error. Please try again"
        in 400..499 -> "Request error. Please check your input"
        in 500..599 -> "Server error. Please try again later"
        else -> "Something went wrong. Please try again"
    }
}