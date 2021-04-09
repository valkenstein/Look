package com.aldredo.look.domain.usecase

import com.aldredo.look.data.repository.StateServerRepository
import com.aldredo.look.domain.state.StateServer

class StateServerUseCase(private val serverRepository: StateServerRepository) {
    suspend fun getStateServer(): StateServer {
        return serverRepository.checkStateServer()
    }
}