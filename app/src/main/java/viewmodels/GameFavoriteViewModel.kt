/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import entidades.GameEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import sql.GameDAO
import sql.GameDatabase

class GameFavoriteViewModel(gameDatabase: GameDatabase) : ViewModel() {

    private val _favoriteList = mutableStateOf<List<GameEntity>>(emptyList())
    val favoriteList: State<List<GameEntity>> = _favoriteList

    // Acceder al DAO
    private val gameFavoriteDAO: GameDAO = gameDatabase.gameDao()

    fun onInit(userId: String) {
        viewModelScope.launch {
            loadFavoriteGames(userId)
        }
    }

    // Método para cargar los juegos favoritos desde la base de datos
    private fun loadFavoriteGames(userId: String) {
        viewModelScope.launch {
            val games = withContext(Dispatchers.IO) {
                gameFavoriteDAO.getAll(userId)
            }

            _favoriteList.value = games
        }
    }
}
