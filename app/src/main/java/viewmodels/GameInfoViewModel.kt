/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import api.API
import entity.Genre
import entity.Platforms

class GameInfoViewModel(private val gameId : Int): ViewModel() {

    var id by mutableStateOf(0)
    private var background by mutableStateOf("")
    var name by mutableStateOf("")
    var description by mutableStateOf("")
    var released by mutableStateOf("")
    var metacritic by mutableStateOf(0)
    var genres : List<Genre> by mutableStateOf(listOf())
    var platforms : List<Platforms> by mutableStateOf(listOf())
    var slug by mutableStateOf("")

    init {
        loadData()
    }

    private fun loadData() {

        API.loadGameDetails(gameId,{ game ->

            id = game.id
            slug = game.slug
            genres = game.genres.sortedBy { it.name }
            platforms = game.platforms.sortedBy { it.platform.name }
            name = game.name
            metacritic = game.metacritic
            background = game.background_image.toString()

            //Si el valor description_raw está vacio, le pasamos el valor description formateado
            description = game.description_raw?.ifEmpty {
                game.description?.replace("<[^>]*>".toRegex(), "") ?: ""
            } ?: ""

        }, {
            println("Error")
        })

    }

}