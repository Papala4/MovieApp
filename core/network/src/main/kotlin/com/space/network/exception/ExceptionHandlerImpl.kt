package com.space.network.exception

import com.space.network.R
import retrofit2.HttpException
import java.io.IOException

class ExceptionHandlerImpl : ExceptionHandler {
    override suspend fun getExceptionByThrowable(throwable: Throwable): BaseException =
        when (throwable) {
            is IOException -> BaseException(
                code = ErrorCode.NETWORK,
                messageRes = R.string.io_message,
                cause = throwable
            )
            is HttpException -> BaseException(
                code = ErrorCode.HTTP,
                httpStatus = throwable.code(),
                messageRes = throwable.toReadableMessageRes()
            )
            else -> BaseException(
                code = ErrorCode.UNKNOWN,
                messageRes = R.string.base_message,
                cause = throwable
            )
        }

    private fun HttpException.toReadableMessageRes(): Int = when (code()) {
        in 300..399 -> R.string.redirect_message
        in 400..499 -> R.string.request_message
        in 500..599 -> R.string.server_message
        else -> R.string.base_message
    }
}