package com.aldredo.look.data.mapper

import com.aldredo.look.data.model.ProfileModel
import com.aldredo.look.domain.dto.ProfileDto

object ProfileMapper {
    fun mappingToDto(profile: ProfileModel?): ProfileDto {
        return ProfileDto(profile?.result?.title ?: "")
    }
}