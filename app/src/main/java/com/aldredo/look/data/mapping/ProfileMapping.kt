package com.aldredo.look.data.mapping

import com.aldredo.look.data.model.ProfileModel
import com.aldredo.look.domain.dto.ProfileDto

object ProfileMapping {
    fun mappingToDto(profile: ProfileModel?): ProfileDto {
        return ProfileDto(profile?.name ?: "")
    }
}