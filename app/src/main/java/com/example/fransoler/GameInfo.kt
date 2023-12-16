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
import interfaces.GameInfoInterface
import ui.components.ShowGameInfo
import ui.theme.AppTheme
import viewmodels.GameInfoViewModel

class GameInfo : AppCompatActivity(), GameInfoInterface {

    //variables globales
    private var platformId: Int = 0
    private var page : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        platformId = intent.getIntExtra(Constant.platformId,0)
        page = intent.getIntExtra(Constant.page,1)

        val favorite = false

        setContent {
            val gameInfoInterface = this@GameInfo // Accede a la instancia de la actividad

            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ShowGameInfo(gameInfoInterface, intent.getIntExtra(Constant.gameId,1), favorite)
                }
            }
        }
    }

    override fun changeFavorite(newFavorite: Boolean, title: String) {
        if(newFavorite){
            Toast.makeText(this,"Añadido a favoritos: $title", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"Eliminado de favoritos: $title", Toast.LENGTH_SHORT).show()
        }
    }

    //Volver atrás
    override fun back(){
        val intent = Intent(this,GameListActivity::class.java)
        intent.putExtra(Constant.platformId, platformId)
        intent.putExtra(Constant.page, page)
        startActivity(intent)
    }

    override fun compartir(game : GameInfoViewModel) {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_SUBJECT, Constant.autor)
        intent.putExtra(Intent.EXTRA_TEXT, Constant.urlGames + game.slug)
        intent.type = "text/plain"
        startActivity(intent)
    }

}