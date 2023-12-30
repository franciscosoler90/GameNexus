/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package sql

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import entidades.GameEntity
import entidades.GenreListConverter


@Database(entities = [GameEntity::class], version = 11)
@TypeConverters(GenreListConverter::class)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDAO
}