package com.aldredo.look.data.repository

import com.aldredo.core.base.room.AppDatabase
import com.aldredo.core.base.room.entity.CookieEntity
import com.aldredo.look.domain.dto.ProfileDto
import com.aldredo.look.domain.state.StateProfile
import java.lang.Exception
import javax.inject.Inject

class ProfileBdRepository @Inject constructor(private val bd: AppDatabase) {
    // тут будем добавлять куку в заголовок
    suspend fun getProfileScreenToBd(): StateProfile {
        return try {
            val lastItem = bd.cookieDao()?.getAll()?.last()
            if (lastItem?.name != null) {
                StateProfile.Result(ProfileDto(lastItem.name))
            } else
                StateProfile.Error("база пуста")
        } catch (e: Exception) {
            StateProfile.Error(e.message.toString())
        }
    }

    suspend fun setProfileScreenToBd(name: String) {
        bd.cookieDao()?.insert(CookieEntity(name = name))
    }
}