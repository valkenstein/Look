package com.aldredo.look.domain.state

import com.aldredo.look.domain.dto.ProfileDto

sealed class StateProfile {
    data class Empty(val message: String) : StateProfile()
    data class Error(val result: ProfileDto) : StateProfile()
}
