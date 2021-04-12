package com.aldredo.look.data.model

data class ProfileModel(
    val result: Result?
) {
    data class Result(val title: String?)
}