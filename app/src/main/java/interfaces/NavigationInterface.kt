/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package interfaces

import entidades.Game
import entidades.ParentPlatform

interface NavigationInterface {
    fun homeRoute()
    fun searchRoute()
    fun favoriteRoute()


    fun onClickPlatform(platform: ParentPlatform.Platform)
    fun onClickGame(game: Game)
}