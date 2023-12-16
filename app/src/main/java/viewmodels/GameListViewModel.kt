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

class GameListViewModel(private val platformId : Int, private val page : Int): ViewModel() {

    var gameList: List<Game> by mutableStateOf(listOf())
    var next: String? by mutableStateOf("")

    init {
        loadData()
    }

    private fun loadData() {
        API.loadGames(platformId,page,{ result ->
            this.gameList = result.result
            this.next = result.next
        }, {
            println("Error")
        })
    }

    fun updatePage(page : Int){
        API.loadGames(platformId,page,{ result ->
            this.gameList = result.result
            this.next = result.next
        }, {
            println("Error")
        })
    }

}