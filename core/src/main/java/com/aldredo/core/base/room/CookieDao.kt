package com.aldredo.core.base.room

import androidx.room.*
import com.aldredo.core.base.room.entity.CookieEntity

@Dao
interface CookieDao {
    @Query("SELECT * FROM CookieEntity")
    fun getAll(): List<CookieEntity?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cookie: CookieEntity?)

    @Delete
    fun delete(cookie: CookieEntity?)
}