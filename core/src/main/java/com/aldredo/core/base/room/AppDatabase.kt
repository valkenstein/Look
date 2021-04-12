package com.aldredo.core.base.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aldredo.core.base.room.entity.CookieEntity

@Database(
    entities = [ CookieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cookieDao(): CookieDao?
}