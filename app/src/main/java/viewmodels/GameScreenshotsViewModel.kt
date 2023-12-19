/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import api.API

class GameScreenshotsViewModel(private val gameId : Long): ViewModel() {

    var screenshotsList : List<String> by mutableStateOf(listOf())

    init {
        loadData()
    }

    private fun loadData() {
        API.loadGameScreenshots(gameId,{ screenshots ->

            val flatList = screenshots.result.flatMap { gameScreenshot ->
                listOf(gameScreenshot.image) // Assuming image is a string in ScreenShot class
            }

            screenshotsList = flatList
        }) {
            println("Error")
        }

    }
}