package com.zizoh.ulesson.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Created by zizoh on 25/November/2020.
 */

object HttpsInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val requestBuilder: Request.Builder = request.newBuilder()

        var stringUrl = request.url.toString()
        stringUrl = stringUrl.replace("%2C", ",")
        requestBuilder.url(stringUrl)

        val newRequest: Request = requestBuilder.build()
        val response: Response?

        try {
            response = chain.proceed(newRequest)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
        return response
    }
}