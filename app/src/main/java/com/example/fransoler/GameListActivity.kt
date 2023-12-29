/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package com.example.fransoler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import common.Constant
import entidades.Game
import entidades.enums.BottomBarState
import interfaces.GameInterface
import ui.components.GameList
import ui.theme.AppTheme
import viewmodels.GameListViewModel

class GameListActivity : AppCompatActivity(), GameInterface {

    private var platformId: Int = 0
    private var currentPage: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        platformId = intent.getIntExtra(Constant.platformId,0)
        currentPage = intent.getIntExtra(Constant.page,1)

        setContent {
            val gameInterface = this@GameListActivity // Accede a la instancia de la actividad
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GameList(gameInterface, platformId, currentPage)
                }
            }
        }
    }

    //Método que se llama al clicar en un juego
    override fun onClickGame(game: Game) {
        val intent = Intent(this,GameInfoActivity::class.java)
        intent.putExtra(Constant.destination, BottomBarState.HOME.ordinal)
        intent.putExtra(Constant.gameId, game.id)
        intent.putExtra(Constant.platformId, platformId)
        intent.putExtra(Constant.page, currentPage)
        startActivity(intent)
    }

    override fun onShareGame(game: Game) {
        //Nada
    }

    override fun onToogleFavorite(favorite: Boolean) {
        //Nada
    }

    override fun back() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun updateForward(gameListViewModel: GameListViewModel) {
        if(gameListViewModel.next != null){
            currentPage++
            gameListViewModel.updatePage(currentPage)
        }
    }

    override fun updatePrevious(gameListViewModel: GameListViewModel) {
        if(currentPage > 1){
            currentPage--
            gameListViewModel.updatePage(currentPage)
        }
    }
}