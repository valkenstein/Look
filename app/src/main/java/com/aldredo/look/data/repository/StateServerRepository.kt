package com.aldredo.look.data.repository

import com.aldredo.look.data.api.PingApi

class StateServerRepository(val pingApi: PingApi) {
    fun checkStateServer(): Boolean {
        //TODO
        return true
    }
}