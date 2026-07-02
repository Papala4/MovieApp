package com.space.network.exception

import androidx.annotation.StringRes

class BaseException(
    val code: ErrorCode,
    val httpStatus: Int? = null,
    @param:StringRes val messageRes: Int,
    cause: Throwable? = null
) : Exception(cause)