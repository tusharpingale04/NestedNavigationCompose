package com.tushar.moviescompose.network

import com.tushar.moviescompose.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response


class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("x-rapidapi-key", BuildConfig.API_KEY)
                .addHeader("x-rapidapi-host", BuildConfig.API_HOST)
                .build()
        )
    }
}