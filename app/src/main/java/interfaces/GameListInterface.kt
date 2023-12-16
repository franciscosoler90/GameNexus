/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package interfaces

import entidades.Game
import viewmodels.GameListViewModel

interface GameListInterface {
    fun onGameClicked(game: Game)

    fun backToPlatforms()

    fun updateForward(viewModel : GameListViewModel)

    fun updatePrevious(viewModel : GameListViewModel)
}