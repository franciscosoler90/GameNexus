/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package interfaces

import entidades.Game
import entidades.Platform

interface NavigationInterface {
    fun homeRoute()
    fun searchRoute()
    fun favoriteRoute()

    fun onClickPlatform(platform: Platform)
    fun onClickGame(game: Game)
}