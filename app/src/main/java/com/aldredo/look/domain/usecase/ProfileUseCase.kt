package com.aldredo.look.domain.usecase

import com.aldredo.look.data.repository.ProfileBdRepository
import com.aldredo.look.data.repository.ProfileRepository
import com.aldredo.look.domain.state.StateProfile
import com.aldredo.look.domain.state.StateScreenBd
import javax.inject.Inject

class ProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val profileBdRepository: ProfileBdRepository
) {
    suspend fun getProfileServer() = profileRepository.getProfileScreen()

    suspend fun getProfileBd() = profileBdRepository.getProfileScreenToBd()

    fun saveCookieToBd(cookie: String) = profileBdRepository.setProfileScreenToBd(cookie)
}