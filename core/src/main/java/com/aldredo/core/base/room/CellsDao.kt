package com.aldredo.core.base.room

import androidx.room.*
import com.aldredo.core.base.room.entity.CookieEntity

@Dao
interface CellsDao {
    @Query("SELECT * FROM CookieEntity")
    fun getAll(): List<CookieEntity?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cells: List<CookieEntity>?)

    @Query("SELECT * FROM CookieEntity WHERE barcode LIKE :searchBarcode")
    fun searchBarcode(searchBarcode: String): CookieEntity?

    @Delete
    fun delete(cookie: CookieEntity?)
}