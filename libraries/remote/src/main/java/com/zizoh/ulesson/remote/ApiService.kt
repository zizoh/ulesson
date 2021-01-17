package com.zizoh.ulesson.remote

import com.zizoh.ulesson.remote.model.NetworkResponse
import retrofit2.http.GET

/**
 * Created by zizoh on 15/January/2021.
 */

interface ApiService {

    @GET("content/grade")
    suspend fun getData(): NetworkResponse
}