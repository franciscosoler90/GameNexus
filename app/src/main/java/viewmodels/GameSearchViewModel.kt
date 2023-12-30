/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import api.API
import entidades.GameEntity

class GameSearchViewModel : ViewModel() {

    var listGames by mutableStateOf<List<GameEntity>>(emptyList())
    var onError: () -> Unit = {} // Manejo de error por defecto, se puede configurar desde fuera

    fun searchGames(query : String, searchPrecise : Boolean, searchExact : Boolean, ordering : String) {
        API.searchGames(
            query,
            searchPrecise = searchPrecise,
            searchExact = searchExact,
            ordering = ordering,
            success = { game ->
            listGames = game.result
        }) {
            onError() // Llama a la función de manejo de errores
        }
    }
}