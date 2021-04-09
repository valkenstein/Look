package com.aldredo.look.domain.state

sealed class StateCode {
    data class Error(val message: String) : StateCode()
    object Result : StateCode()
}