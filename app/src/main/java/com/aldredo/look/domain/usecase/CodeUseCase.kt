package com.aldredo.look.domain.usecase

import com.aldredo.look.data.repository.CodeRepository
import com.aldredo.look.domain.state.StateCode
import javax.inject.Inject

class CodeUseCase @Inject constructor(private val codeRepository: CodeRepository) {
    suspend fun putCode(code: String): StateCode {
        return codeRepository.putCode(code)
    }
}