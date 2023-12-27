/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import api.API
import entidades.DetailScreenEvent
import entidades.Game

class GameInfoViewModel(private val gameId: Long): ViewModel() {

    var game by mutableStateOf(Game(0L, "", "", "", null, "", "", "", false, 0, 0.0f, emptyList(), emptyList(), emptyList(), emptyList()))
    var cleanDescription by mutableStateOf("")
    var listGenres by mutableStateOf<List<String>>(emptyList())
    var listPlatforms by mutableStateOf<List<String>>(emptyList())
    var listPublishers by mutableStateOf<List<String>>(emptyList())
    var listDevelopers by mutableStateOf<List<String>>(emptyList())
    var listScreenshots by mutableStateOf<List<String>>(emptyList())
    var isFavorite by mutableStateOf(false)

    init {
        loadData()
    }

    fun onEvent(event: DetailScreenEvent) {
        // Lógica de eventos si es necesaria
    }

    private fun loadData() {
        loadGameDetails()
        loadGameScreenshots()
    }

    private fun loadGameDetails() {
        API.loadGameDetails(gameId,{ game ->

            this.game = game

            val publisherFlatList = game.publishers.flatMap { publishers -> listOf(publishers.name) }
            listPublishers = publisherFlatList

            val genresFlatList = game.genres.flatMap { genres -> listOf(genres.name) }
            listGenres = genresFlatList

            val platformsFlatList = game.platforms.flatMap { platforms -> listOf(platforms.platform.name) }
            listPlatforms = platformsFlatList

            val developersFlatList = game.developers.flatMap { developers -> listOf(developers.name) }
            listDevelopers = developersFlatList

            //Si el valor description_raw está vacio, le pasamos el valor description formateado
            cleanDescription = game.description_raw.ifEmpty {
                game.description.replace("<[^>]*>".toRegex(), "")
            }

        }) {
            println("Error")
        }

    }

    private fun loadGameScreenshots() {
        API.loadGameScreenshots(gameId,{ screenshots ->

            val flatList = screenshots.result.flatMap { gameScreenshot -> listOf(gameScreenshot.image) }
            listScreenshots = flatList
        }) {
            println("Error")
        }


    }
}
