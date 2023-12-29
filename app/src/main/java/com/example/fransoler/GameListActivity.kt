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
import interfaces.GameListInterface
import ui.components.GameList
import ui.theme.AppTheme
import viewmodels.GameListViewModel

class GameListActivity : AppCompatActivity(), GameListInterface {

    private var platformId: Int = 0
    private var currentPage : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        platformId = intent.getIntExtra(Constant.platformId,0)
        currentPage = intent.getIntExtra(Constant.page,1)

        setContent {
            val gameListInterface = this@GameListActivity // Accede a la instancia de la actividad
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GameList(gameListInterface, platformId, currentPage)
                }
            }
        }
    }

    //Método que se llama al clicar en un juego
    override fun onGameClicked(game: Game) {
        val intent = Intent(this,GameInfoActivity::class.java)
        intent.putExtra(Constant.gameId, game.id)
        intent.putExtra(Constant.platformId, platformId)
        intent.putExtra(Constant.page, currentPage)
        startActivity(intent)
    }

    override fun backToPlatforms() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun updateForward(viewModel: GameListViewModel) {
        if(viewModel.next != null){
            currentPage++
            viewModel.updatePage(currentPage)
        }
    }

    override fun updatePrevious(viewModel: GameListViewModel) {
        if(currentPage > 1){
            currentPage--
            viewModel.updatePage(currentPage)
        }
    }
}