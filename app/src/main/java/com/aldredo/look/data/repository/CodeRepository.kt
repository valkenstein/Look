package com.aldredo.look.data.repository

import com.aldredo.look.data.api.CodeApi
import com.aldredo.look.data.mapping.CodeMapping
import com.aldredo.look.domain.state.StateCode
import java.lang.Exception

class CodeRepository constructor(private val codeApi: CodeApi) {
    suspend fun putCode(code: String): StateCode {
        return try {
            val hashMap = HashMap<String, String>().apply {
                this["_id"] = code
            }
            val result = codeApi.putCode(hashMap)
            when (result.code()) {
                204 -> {
                    StateCode.Success
                }
                200 -> {
                    StateCode.Result(CodeMapping.mappingCodeToDto(result.body()))
                }
                else -> {
                    StateCode.Error("")
                }
            }
        } catch (e: Exception) {
            StateCode.Error(e.message.toString())
        }
    }
}