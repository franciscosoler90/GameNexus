/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import api.API
import entidades.Game

class GameSearchViewModel : ViewModel() {

    var listGames by mutableStateOf<List<Game>>(emptyList())
    var onError: () -> Unit = {} // Manejo de error por defecto, se puede configurar desde fuera

    fun searchGames(query : String) {
        API.searchGames(query, { game ->
            listGames = game.result
        }) {
            onError() // Llama a la función de manejo de errores
        }
    }
}