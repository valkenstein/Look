package com.aldredo.look.domain.usecase

import com.aldredo.look.data.repository.ProfileBdRepository
import com.aldredo.look.data.repository.ProfileRepository
import com.aldredo.look.domain.state.StateProfile
import javax.inject.Inject

class ProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val profileBdRepository: ProfileBdRepository
) {
    suspend fun getProfileServer(): StateProfile {
        return profileRepository.getProfileScreen()
    }

    suspend fun getProfileBd(): StateProfile {
        return profileBdRepository.getProfileScreenToBd()
    }

    suspend fun setProfileScreen(name: String) {
        profileBdRepository.setProfileScreenToBd(name)
    }
}