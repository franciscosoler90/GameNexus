/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import api.API
import entidades.ScreenShot

class GameScreenshotsViewModel(private val gameId : Int): ViewModel() {

    var screenShots : List<ScreenShot> by mutableStateOf(listOf())

    init {
        loadData()
    }

    private fun loadData() {

        API.loadGameScreenshots(gameId,{ screenshots ->
            screenShots = screenshots.result
        }, {
            println("Error")
        })

    }
}