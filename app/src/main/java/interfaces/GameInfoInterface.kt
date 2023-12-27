/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package interfaces

import entidades.Game

interface GameInfoInterface {

    fun changeFavorite(newFavorite : Boolean)

    fun back()

    fun compartir(game : Game)
}