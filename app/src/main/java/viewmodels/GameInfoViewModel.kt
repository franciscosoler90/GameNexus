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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameInfoViewModel(private val gameId: Long): ViewModel() {

    var id by mutableStateOf(0L)
    var background by mutableStateOf("")
    var name by mutableStateOf("")
    var description by mutableStateOf("")
    var released by mutableStateOf("")
    var slug by mutableStateOf("")
    var rating by mutableStateOf("")
    var metacritic by mutableStateOf(0)
    var genres : List<String> by mutableStateOf(listOf())
    var platforms : List<String> by mutableStateOf(listOf())
    var publishers : List<String> by mutableStateOf(listOf())
    var developers : List<String> by mutableStateOf(listOf())
    var isFavorite by mutableStateOf(false)


    private val _uiState = MutableStateFlow(DetailScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        loadData()
    }

    fun onEvent(event: DetailScreenEvent) {
        when (event) {

            else -> {}
        }
    }

    private fun loadData() {

        API.loadGameDetails(gameId,{ game ->

            id = game.id
            slug = game.slug
            name = game.name
            rating = game.rating.toString()
            metacritic = game.metacritic
            released = game.released.toString()
            background = game.background_image.toString()
            isFavorite = game.isFavorite

            val publisherFlatList = game.publishers.flatMap { publishers -> listOf(publishers.name) }
            publishers = publisherFlatList

            val genresFlatList = game.genres.flatMap { genres -> listOf(genres.name) }
            genres = genresFlatList

            val platformsFlatList = game.platforms.flatMap { platforms -> listOf(platforms.platform.name) }
            platforms = platformsFlatList

            val developersFlatList = game.developers.flatMap { developers -> listOf(developers.name) }
            developers = developersFlatList

            //Si el valor description_raw está vacio, le pasamos el valor description formateado
            description = game.description_raw?.ifEmpty {
                game.description?.replace("<[^>]*>".toRegex(), "") ?: ""
            } ?: ""

        }) {
            println("Error")
        }

    }

}