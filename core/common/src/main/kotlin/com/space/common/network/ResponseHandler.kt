package com.space.common.network

import kotlinx.coroutines.flow.flow
import java.io.IOException
import retrofit2.Response
import retrofit2.HttpException


class ResponseHandler {
    fun <T> apiCall(apiCall: suspend () -> Response<T>) = flow {
        emit(ApiResult.Loading)
        try {
            val response = apiCall()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                emit(ApiResult.Success(body))
            } else {
                emit(ApiResult.Error("Empty response"))
            }
        } catch (e: Exception) {
            emit(ApiResult.Error(e.toAppException().message))
        }
    }
}

sealed class AppException : Exception() {
    data class Network(override val message: String) : AppException()
    data class Http(override val message: String) : AppException()
    data class Unknown(override val message: String) : AppException()
}

fun Throwable.toAppException() = when (this) {
    is IOException -> AppException.Network(message.orEmpty())
    is HttpException -> AppException.Http(message.orEmpty())
    else -> AppException.Unknown(message.orEmpty())
}
