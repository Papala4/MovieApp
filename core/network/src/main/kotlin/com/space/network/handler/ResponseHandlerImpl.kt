package com.space.network.handler

import com.space.network.R
import com.space.network.api_result.ApiResult
import com.space.network.exception.BaseException
import com.space.network.exception.ErrorCode
import com.space.network.exception.ExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class ResponseHandlerImpl(
    private val exceptionHandler: ExceptionHandler
) : ResponseHandler {
    override fun <T> apiCall(apiCall: suspend () -> Response<T>): Flow<ApiResult<T>> = flow {
        emit(ApiResult.Loading)
        try {
            val response = apiCall()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                emit(ApiResult.Success(body))
            } else {
                emit(
                    ApiResult.Error(
                        BaseException(
                            code = ErrorCode.UNKNOWN,
                            messageRes = R.string.empty_response_message
                        )
                    )
                )
            }
        } catch (e: Exception) {
            emit(ApiResult.Error(exceptionHandler.getExceptionByThrowable(e)))
        }
    }
}
