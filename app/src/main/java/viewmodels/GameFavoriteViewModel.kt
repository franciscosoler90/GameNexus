/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import entidades.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import sql.GameDAO
import sql.GameDatabase

class GameFavoriteViewModel(gameDatabase: GameDatabase) : ViewModel() {

    private val _favoriteList = mutableStateOf<List<Game>>(emptyList())
    val favoriteList: State<List<Game>> = _favoriteList

    // Acceder al DAO
    private val gameFavoriteDAO: GameDAO = gameDatabase.gameDao()

    fun onInit() {
        viewModelScope.launch {
            loadFavoriteGames()
        }
    }

    // Método para cargar los juegos favoritos desde la base de datos
    private fun loadFavoriteGames() {
        viewModelScope.launch {
            val games = withContext(Dispatchers.IO) {
                gameFavoriteDAO.getAll()
            }

            _favoriteList.value = games
        }
    }
}
