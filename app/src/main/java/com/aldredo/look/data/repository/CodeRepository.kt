package com.aldredo.look.data.repository

import android.util.Log
import com.aldredo.look.data.api.CodeApi
import com.aldredo.look.data.mapping.CodeMapping
import com.aldredo.look.domain.state.StateCode
import java.lang.Exception
import javax.inject.Inject

class CodeRepository @Inject constructor(private val codeApi: CodeApi) {
    suspend fun putCode(code: String): StateCode {
        return try {
            val hashMap = HashMap<String, String>().apply {
                put("code", code)
            }
            Log.e("CodeRepository", code)
            val result = codeApi.putCode(hashMap)
            when (result.code()) {
                204 -> {
                    StateCode.Success
                }
                200 -> {
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
}