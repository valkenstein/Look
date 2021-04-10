package com.aldredo.look.data.api

import com.aldredo.look.data.model.ProfileModel
import retrofit2.Response
import retrofit2.http.GET

interface ProfileApi {
    @GET("profile")
    suspend fun getProfile(): Response<ProfileModel>
}