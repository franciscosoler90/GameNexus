/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package interfaces

import entidades.Game
import entidades.GameEntity
import viewmodels.GameListViewModel

interface GameInterface {
    fun back()
    fun updateForward(gameListViewModel : GameListViewModel)
    fun updatePrevious(gameListViewModel : GameListViewModel)
    fun onClickGame(game: GameEntity)
    fun onShareGame(game: Game)
    fun onToogleFavorite(favorite: Boolean)
}