package com.aldredo.look.domain.state

sealed class StateServer {
    data class Error(val message: String) : StateServer()
    object Success : StateServer()
}