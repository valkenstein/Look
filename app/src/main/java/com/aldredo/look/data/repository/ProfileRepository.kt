package com.aldredo.look.data.repository

import com.aldredo.look.data.api.ProfileApi
import com.aldredo.look.data.mapping.ProfileMapping
import com.aldredo.look.domain.dto.ProfileDto
import com.aldredo.look.domain.state.StateProfile
import java.lang.Exception
import javax.inject.Inject

class ProfileRepository constructor(private val profileApi: ProfileApi) {
    suspend fun getProfileScreen(): StateProfile {
        return try {
            val profile = profileApi.getProfile()
            //    if (profile.code() == 204) {
            StateProfile.Result(ProfileMapping.mappingToDto(profile.body()))
            //  }
        } catch (e: Exception) {
            StateProfile.Error(e.message.toString())
        }
    }
}