package com.aldredo.look.data.repository

import android.util.Log
import com.aldredo.core.base.interceptor.model.CookieModel
import com.aldredo.look.data.api.CodeApi
import com.aldredo.look.data.api.ProfileApi
import com.aldredo.look.data.mapping.ProfileMapping
import com.aldredo.look.domain.state.StateProfile
import java.lang.Exception
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val profileApi: ProfileApi,
    private val cookieModel: CookieModel
) {
    suspend fun getProfileScreen(): StateProfile {
        return try {
            val profile = profileApi.getProfile()
            //    if (profile.code() == 204) {
            Log.e("ProfileRepository", cookieModel.value.toString())
            StateProfile.Result(ProfileMapping.mappingToDto(profile.body()))
            //  }
        } catch (e: Exception) {
            StateProfile.Error(e.message.toString())
        }
    }
}