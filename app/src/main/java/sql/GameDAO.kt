/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package sql

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import entidades.Game

@Dao
interface GameDAO {
    @Query("SELECT * FROM game")
    fun getAll(): List<Game>

    @Insert
    fun insertAll(vararg games: Game)

    @Delete
    fun delete(game: Game)
}