package com.aldredo.look.domain.usecase

import com.aldredo.look.data.repository.CodeRepository
import javax.inject.Inject

class CodeUseCase @Inject constructor(private val codeRepository: CodeRepository) {
    suspend fun putCode(code: String) = codeRepository.putCode(code)
}