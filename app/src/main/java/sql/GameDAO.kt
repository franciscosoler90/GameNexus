/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package sql

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import entidades.GameEntity

@Dao
interface GameDAO {
    @Query("SELECT * FROM favorite_games WHERE userId = :userId")
    fun getAll(userId : String): List<GameEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg games: GameEntity)

    @Delete
    fun delete(game: GameEntity)

    @Query("SELECT COUNT(*) FROM favorite_games WHERE id = :gameId AND userId = :userId")
    fun isGameFavorite(gameId: Long, userId : String): Int

}