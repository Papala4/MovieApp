package com.space.network.handler

import com.space.network.result.ApiResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ResponseHandler {
    fun <T> apiCall(apiCall: suspend () -> Response<T>): Flow<ApiResult<T>>
}