package com.taurus.modernandroiddevelopmentkata.network

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response

class RequestInterceptor : Interceptor {
  override fun intercept(chain: Chain): Response {
    val originalRequest = chain.request()
    val originalHttpUrl = originalRequest.url()

    val url = originalHttpUrl.newBuilder()
        .addQueryParameter(PARAMETER_NAME, PARAMETER_KEY)
        .build()

    val request = originalRequest.newBuilder().url(url).build()

    return chain.proceed(request)
  }

  private companion object {
    const val PARAMETER_NAME = "api_key"
    const val PARAMETER_KEY = "847f6b4e44e8451ffa91002efdee1e62"
  }
}