package com.aldredo.look.data.repository

import com.aldredo.look.data.api.ProfileApi
import com.aldredo.look.domain.state.StateProfile
import java.lang.Exception

class ProfileRepository(private val profileApi: ProfileApi) {
    fun getProfileScreen(): StateProfile {
        return try {
            StateProfile.Result
        } catch (e: Exception) {
            StateProfile.Error(e.message.toString())
        }
    }
}