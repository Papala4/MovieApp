package com.space.network.interceptor

import com.space.network.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val requestBuilder = originalRequest.newBuilder()
            .addHeader("accept", "application/json")
            .addHeader("Authorization", "Bearer ${BuildConfig.AUTH_TOKEN}")

        return chain.proceed(requestBuilder.build())
    }
}