/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package interfaces

import viewmodels.GameInfoViewModel

interface GameInfoInterface {

    fun changeFavorite(newFavorite : Boolean)

    fun back()

    fun compartir(game : GameInfoViewModel)
}