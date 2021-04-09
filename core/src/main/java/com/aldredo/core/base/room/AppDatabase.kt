package com.aldredo.core.base.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aldredo.core.base.room.entity.CellsEntity


@Database(
    entities = [ CellsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cellsDao(): CellsDao?
}