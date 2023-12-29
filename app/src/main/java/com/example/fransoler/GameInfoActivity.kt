/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package com.example.fransoler

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import common.Constant
import entidades.Game
import utilidades.shareLink
import interfaces.GameInterface
import ui.components.GameInfo
import ui.theme.AppTheme
import viewmodels.GameInfoViewModel
import viewmodels.GameListViewModel

class GameInfoActivity : AppCompatActivity(), GameInterface {

    private var platformId: Int = 0
    private var page : Int = 1
    private val gameInfoViewModel = GameInfoViewModel()
    private val gameInterface = this@GameInfoActivity // Accede a la instancia de la actividad

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        platformId = intent.getIntExtra(Constant.platformId,0)
        page = intent.getIntExtra(Constant.page,1)

        val gameId = intent.getLongExtra(Constant.gameId,0)

        setContent {

            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    gameInfoViewModel.onInit(gameId)
                    GameInfo(gameInfoViewModel.game, gameInfoViewModel, gameInterface)
                }
            }
        }
    }


    //Volver atrás
    override fun back(){
        if(platformId > 0 && page > 0){
            //GameList Activity
            val intent = Intent(baseContext,GameListActivity::class.java)
            intent.putExtra(Constant.platformId, platformId)
            intent.putExtra(Constant.page, page)
            startActivity(intent)
        }else{
            //Search Activity
            val intent = Intent(baseContext,SearchActivity::class.java)
            startActivity(intent)
        }

    }

    override fun updateForward(gameListViewModel: GameListViewModel) {
        //Nada
    }

    override fun updatePrevious(gameListViewModel: GameListViewModel) {
        //Nada
    }

    override fun onClickGame(game: Game) {
        //Nada
    }

    //Compartir
    override fun onShareGame(game: Game) {
        this.shareLink(game)
    }

    override fun onFavoriteGame(game: Game) {
        if(game.isFavorite){
            Toast.makeText(baseContext,R.string.addFavorite, Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(baseContext,R.string.deleteFavorite, Toast.LENGTH_SHORT).show()
        }
    }

}