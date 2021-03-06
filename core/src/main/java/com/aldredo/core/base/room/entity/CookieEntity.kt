package com.aldredo.core.base.room.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity
data class CookieEntity(
    @PrimaryKey()
    val id: String = "id",
    val cookie: String = ""
)