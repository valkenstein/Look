package com.aldredo.look.data.api

import retrofit2.http.GET

interface ProfileApi {
    @GET("profile")
    suspend fun getProfile():  //TODO
}