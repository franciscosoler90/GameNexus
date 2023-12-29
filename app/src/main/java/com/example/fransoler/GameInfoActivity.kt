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
import androidx.room.Room
import common.Constant
import entidades.Game
import interfaces.GameInterface
import sql.GameDatabase
import ui.components.GameInfo
import ui.theme.AppTheme
import viewmodels.GameInfoViewModel
import viewmodels.GameListViewModel

class GameInfoActivity : AppCompatActivity(), GameInterface {

    private var platformId: Int = 0
    private var page : Int = 1
    private var destination : Int = 1

    private lateinit var gameInfoViewModel: GameInfoViewModel
    private val gameInterface = this@GameInfoActivity // Accede a la instancia de la actividad

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gameDatabase = Room.databaseBuilder(applicationContext, GameDatabase::class.java, "game-db").fallbackToDestructiveMigration().build()
        gameInfoViewModel = GameInfoViewModel(gameDatabase)

        platformId = intent.getIntExtra(Constant.platformId,0)
        page = intent.getIntExtra(Constant.page,1)
        destination = intent.getIntExtra(Constant.destination,1)

        val gameId = intent.getLongExtra(Constant.gameId,0)

        setContent {

            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GameInfo(gameId, gameInfoViewModel, gameInterface)
                }
            }
        }
    }

    //Volver atrás
    override fun back(){

        when (destination) {
            0 -> {
                // Lógica para manejar el caso de Home
                val intent = Intent(baseContext,GameListActivity::class.java)
                intent.putExtra(Constant.platformId, platformId)
                intent.putExtra(Constant.page, page)
                startActivity(intent)
            }
            1 -> {
                // Lógica para manejar el caso de Search
                val intent = Intent(baseContext,SearchActivity::class.java)
                startActivity(intent)
            }
            2 -> {
                // Lógica para manejar el caso de Favorite
                val intent = Intent(baseContext,FavoriteActivity::class.java)
                startActivity(intent)
            }

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
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, Constant.appName)
            putExtra(Intent.EXTRA_TEXT, Constant.urlGames + game.slug)
        }
        startActivity(Intent.createChooser(shareIntent, null))
    }

    override fun onToogleFavorite(favorite: Boolean) {
        if(favorite){
            Toast.makeText(baseContext,R.string.addFavorite, Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(baseContext,R.string.deleteFavorite, Toast.LENGTH_SHORT).show()
        }
    }

}