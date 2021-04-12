package com.aldredo.look.data.repository

import com.aldredo.core.base.interceptor.model.CookieModel
import com.aldredo.core.base.room.AppDatabase
import com.aldredo.core.base.room.entity.CookieEntity
import com.aldredo.look.domain.dto.ProfileDto
import com.aldredo.look.domain.state.StateScreenBd
import java.lang.Exception
import javax.inject.Inject

class ProfileBdRepository @Inject constructor(
    private val bd: AppDatabase,
    private val cookieModel: CookieModel
) {
    fun getProfileScreenToBd(): StateScreenBd {
        return try {
            val lastItem = bd.cookieDao()?.getAll()?.last()
            if (lastItem?.cookie != null) {
                //cookieModel.value = lastItem.cookie
                StateScreenBd.Result(ProfileDto(lastItem.cookie))
            } else
                StateScreenBd.Empty("база пуста")
        } catch (e: Exception) {
            StateScreenBd.Empty(e.message.toString())
        }
    }

    fun setProfileScreenToBd(cookie: String) {
        //cookieModel.value = cookie
        bd.cookieDao()?.insert(CookieEntity(cookie = cookie))
    }
}