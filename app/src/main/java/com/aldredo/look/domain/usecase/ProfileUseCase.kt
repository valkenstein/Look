package com.aldredo.look.domain.usecase

import com.aldredo.look.data.repository.ProfileRepository
import com.aldredo.look.domain.state.StateProfile
import javax.inject.Inject

class ProfileUseCase @Inject constructor(private val profileRepository: ProfileRepository) {
    suspend fun getProfile(): StateProfile {
        return profileRepository.getProfileScreen()
    }
}