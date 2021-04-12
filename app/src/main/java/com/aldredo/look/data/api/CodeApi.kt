package com.aldredo.look.data.api

import com.aldredo.look.data.model.CodeModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CodeApi {
    @POST("code")
    suspend fun putCode(@Body bodyRequest: HashMap<Any, Any>): Response<CodeModel>
}