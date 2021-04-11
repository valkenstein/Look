package com.aldredo.look.domain.state

import com.aldredo.look.domain.dto.ProfileDto

sealed class StateProfile {
    data class Result(val result: ProfileDto) : StateProfile()
    data class Error(val message: String) : StateProfile()
}
