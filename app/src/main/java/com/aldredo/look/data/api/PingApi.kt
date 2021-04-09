package com.aldredo.look.data.api

import retrofit2.Response
import retrofit2.http.GET

interface PingApi {
    @GET("ping")
    suspend fun getStateServer(): Response<Any>
}