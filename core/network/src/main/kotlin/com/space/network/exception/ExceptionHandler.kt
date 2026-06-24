package com.space.network.exception

interface ExceptionHandler {
    suspend fun getExceptionByThrowable(throwable: Throwable) : BaseException
}