package com.aldredo.look.data.repository

import com.aldredo.look.data.api.CodeApi
import com.aldredo.look.domain.state.StateCode
import java.lang.Exception
import javax.inject.Inject

class CodeRepository @Inject constructor(private val codeApi: CodeApi) {
    suspend fun putCode(code: String): StateCode {
        return try {

            StateCode.Result
        } catch (e: Exception) {
            StateCode.Error(e.message.toString())
        }
    }
}