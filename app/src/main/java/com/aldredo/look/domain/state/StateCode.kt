package com.aldredo.look.domain.state

import com.aldredo.look.domain.dto.CodeDto

sealed class StateCode {
    data class Error(val message: String) : StateCode()
    data class Result(val result: CodeDto) : StateCode()
    object Success : StateCode()
}