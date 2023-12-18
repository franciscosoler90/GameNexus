/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package com.example.fransoler

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import common.Constant
import common.shareLink
import interfaces.GameInfoInterface
import ui.components.ShowGameInfo
import ui.theme.AppTheme
import viewmodels.GameInfoViewModel

class GameInfo : AppCompatActivity(), GameInfoInterface {

    private var platformId: Int = 0
    private var page : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        platformId = intent.getIntExtra(Constant.platformId,0)
        page = intent.getIntExtra(Constant.page,1)

        setContent {
            val gameInfoInterface = this@GameInfo // Accede a la instancia de la actividad
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ShowGameInfo(gameInfoInterface, intent.getLongExtra(Constant.gameId,1))
                }
            }
        }
    }

    override fun changeFavorite(newFavorite: Boolean) {
        if(newFavorite){
            Toast.makeText(this,R.string.addFavorite, Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,R.string.deleteFavorite, Toast.LENGTH_SHORT).show()
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
        this.shareLink(game)
    }

}