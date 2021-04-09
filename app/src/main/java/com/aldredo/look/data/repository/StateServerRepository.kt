package com.aldredo.look.data.repository

import com.aldredo.look.data.api.PingApi
import com.aldredo.look.domain.state.StateServer
import java.lang.Exception
import javax.inject.Inject

class StateServerRepository @Inject constructor(private val pingApi: PingApi) {
    fun checkStateServer(): StateServer {
        return try {
            StateServer.Result
        } catch (e: Exception) {
            StateServer.Error(e.message.toString())
        }
    }
}