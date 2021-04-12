package com.aldredo.look.data.repository

import android.util.Log
import com.aldredo.core.base.interceptor.model.CookieModel
import com.aldredo.look.data.api.CodeApi
import com.aldredo.look.data.mapping.CodeMapping
import com.aldredo.look.domain.state.StateCode
import java.lang.Exception
import javax.inject.Inject

class CodeRepository @Inject constructor(
    private val codeApi: CodeApi,
    private val cookieModel: CookieModel
) {
    suspend fun putCode(code: String) = try {
        val hashMap = HashMap<Any, Any>().apply {
            put("code", code)
        }
        val result = codeApi.putCode(hashMap)
        when (result.code()) {
            204 -> {
                StateCode.Success
            }
            200 -> {
                Log.e("cookieModel.value", result.body()?.result?._id.toString())
                StateCode.Result(CodeMapping.mappingCodeToDto(result.body()))
            }
            else -> {
                StateCode.Error("ошибка сервера")
            }
        }
    } catch (e: Exception) {
        StateCode.Error(e.message.toString())
    }
}