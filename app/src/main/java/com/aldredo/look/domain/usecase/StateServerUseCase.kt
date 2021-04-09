package com.aldredo.look.domain.usecase

import com.aldredo.look.data.repository.StateServerRepository
import com.aldredo.look.domain.state.StateServer
import javax.inject.Inject

class StateServerUseCase @Inject constructor(private val serverRepository: StateServerRepository) {
    suspend fun getStateServer(): StateServer {
        return serverRepository.checkStateServer()
    }
}