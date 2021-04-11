package com.aldredo.look.domain.state

import com.aldredo.look.domain.dto.ProfileDto

sealed class StateScreenBd {
    data class Empty(val message: String) : StateScreenBd()
    data class Result(val result: ProfileDto) : StateScreenBd()
}