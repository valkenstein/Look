package com.aldredo.look.data.repository

import com.aldredo.look.data.api.CodeApi
import com.aldredo.look.data.mapper.CodeMapper
import com.aldredo.look.domain.state.StateCode
import java.lang.Exception
import javax.inject.Inject

class CodeRepository @Inject constructor(
    private val codeApi: CodeApi
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
                val cookie = result.headers()["Set-Cookie"]
                StateCode.Result(CodeMapper.mappingCodeToDto(result.body(), cookie))
            }
            else -> {
                StateCode.Error("ошибка сервера")
            }
        }
    } catch (e: Exception) {
        StateCode.Error(e.message.toString())
    }
}