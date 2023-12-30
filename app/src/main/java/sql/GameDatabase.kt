/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package sql

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import entidades.DeveloperTypeConverter
import entidades.Game
import entidades.GenreTypeConverter
import entidades.PlatformTypeConverter
import entidades.PublisherTypeConverter

@Database(entities = [Game::class], version = 9)
@TypeConverters(PlatformTypeConverter::class, GenreTypeConverter::class, DeveloperTypeConverter::class, PublisherTypeConverter::class)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDAO
}