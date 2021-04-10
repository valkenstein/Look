package com.aldredo.look.data.repository

import com.aldredo.look.data.api.PingApi
import com.aldredo.look.domain.state.StateServer
import java.lang.Exception
import javax.inject.Inject

class StateServerRepository @Inject constructor(private val pingApi: PingApi) {
    suspend fun checkStateServer(): StateServer {
        return try {
            val ping = pingApi.getStateServer()
            if (ping.code() == 204) {
                StateServer.Success
            } else {
                StateServer.Error("нет доступа к серверу")
            }
        } catch (e: Exception) {
            StateServer.Error("нет доступа к серверу")
        }
    }
}