/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package com.example.fransoler

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import common.Constant
import common.ContextUtilities
import entidades.Game
import entidades.GameEntity
import interfaces.GameInterface
import sql.GameDatabase
import ui.components.game.GameInfo
import ui.theme.AppTheme
import viewmodels.GameInfoViewModel
import viewmodels.GameListViewModel

class GameInfoActivity : AppCompatActivity(), GameInterface {

    private var platformId: Int = 0
    private var page : Int = 1
    private var destination : Int = 1

    private lateinit var gameInfoViewModel: GameInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gameDatabase = Room.databaseBuilder(applicationContext, GameDatabase::class.java, "game-db").fallbackToDestructiveMigration().build()
        gameInfoViewModel = GameInfoViewModel(gameDatabase)

        platformId = intent.getIntExtra(Constant.platformId,0)
        page = intent.getIntExtra(Constant.page,1)
        destination = intent.getIntExtra(Constant.destination,1)

        val gameId = intent.getLongExtra(Constant.gameId,0)

        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid

        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GameInfo(userId, gameId, gameInfoViewModel, this@GameInfoActivity)
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

    override fun onClickGame(game: GameEntity) {
        //Nada
    }

    //Compartir
    override fun onShareGame(game: Game) {
        ContextUtilities.shareGame(this, game)
    }

    override fun onToogleFavorite(favorite: Boolean) {
        ContextUtilities.onToogleFavorite(this, favorite)

    }


}