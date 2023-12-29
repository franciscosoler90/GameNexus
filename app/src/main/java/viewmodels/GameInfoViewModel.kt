/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import api.API
import entidades.Game
import entidades.GameInfoScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import sql.GameDAO
import sql.GameDatabase

class GameInfoViewModel(gameDatabase: GameDatabase) : ViewModel() {

    private val _uiState = MutableStateFlow(GameInfoScreenState())
    val uiState = _uiState.asStateFlow()

    var cleanDescription by mutableStateOf("")
    var listScreenshots by mutableStateOf<List<String>>(emptyList())

    // Acceder al DAO
    private val gameFavoriteDAO: GameDAO = gameDatabase.gameDao()

    fun onInit(gameId: Long) {
        viewModelScope.launch {
            loadGameDetails(gameId)
            loadGameScreenshots(gameId)
            checkIfGameIsFavorite(gameId)
        }
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

    private suspend fun checkIfGameIsFavorite(gameId : Long) {
        val result = withContext(Dispatchers.IO) {
            gameFavoriteDAO.isGameFavorite(gameId)
        }

        _uiState.update { it.copy(isFavoriteGame = result > 0) }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            val game = uiState.value.game ?: return@launch
            if (uiState.value.isFavoriteGame) {
                deleteFavorite(game)
            } else {
                addFavorite(game)
            }
        }
    }

    private suspend fun addFavorite(game: Game) {
        withContext(Dispatchers.IO) {
            gameFavoriteDAO.insertAll(game)
            checkIfGameIsFavorite(game.id)
        }
    }

    private suspend fun deleteFavorite(game: Game) {
        withContext(Dispatchers.IO) {
            gameFavoriteDAO.delete(game)
            checkIfGameIsFavorite(game.id)
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