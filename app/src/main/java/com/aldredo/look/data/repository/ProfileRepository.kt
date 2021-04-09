package com.aldredo.look.data.repository

import com.aldredo.look.data.api.ProfileApi
import com.aldredo.look.domain.state.StateProfile
import java.lang.Exception
import javax.inject.Inject

class ProfileRepository @Inject constructor (private val profileApi: ProfileApi) {
    fun getProfileScreen(): StateProfile {
        return try {
           return TODO()
            //StateProfile.Result("")
        } catch (e: Exception) {
            StateProfile.Error(e.message.toString())
        }
    }
}