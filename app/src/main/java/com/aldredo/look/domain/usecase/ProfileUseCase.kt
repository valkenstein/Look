package com.aldredo.look.domain.usecase

import com.aldredo.look.data.repository.ProfileRepository
import com.aldredo.look.domain.state.StateProfile

class ProfileUseCase(private val profileRepository: ProfileRepository) {
    suspend fun getProfile(): StateProfile {
        return profileRepository.getProfileScreen()
    }
}