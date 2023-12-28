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
import entidades.DetailScreenState
import entidades.Game
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameInfoViewModel(): ViewModel() {

    private val _uiState = MutableStateFlow(DetailScreenState())
    val uiState = _uiState.asStateFlow()

    var game by mutableStateOf(Game(0L, "", "", "", null, "", "", "", false, 0, 0.0f, emptyList(), emptyList(), emptyList(), emptyList()))
    var cleanDescription by mutableStateOf("")
    var listScreenshots by mutableStateOf<List<String>>(emptyList())

    fun onInit(gameId: Long) {
        loadGameDetails(gameId)
        loadGameScreenshots(gameId)
    }

    fun onEvent(event: DetailScreenEvent) {
        // Lógica de eventos si es necesaria
    }

    private fun loadGameDetails(gameId : Long) {
        API.loadGameDetails(gameId,{ game ->

            _uiState.update { it.copy(game = game) }

            //Si el valor description_raw está vacio, le pasamos el valor description formateado
            cleanDescription = game.description_raw.ifEmpty {
                game.description.replace("<[^>]*>".toRegex(), "")
            }

        }) {
            println("Error - GameInfoViewModel - loadGameDetails")
        }

    }

    private fun loadGameScreenshots(gameId : Long) {
        API.loadGameScreenshots(gameId,{ screenshots ->

            val flatList = screenshots.result.flatMap { gameScreenshot -> listOf(gameScreenshot.image) }
            listScreenshots = flatList

            _uiState.update { it.copy(listScreenshots = listScreenshots) }
        }) {
            println("Error - GameInfoViewModel - loadGameScreenshots")
        }

    }

}