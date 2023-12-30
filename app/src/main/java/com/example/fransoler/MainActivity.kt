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
import entidades.Platform
import interfaces.NavigationInterface
import interfaces.PlatformInterface
import ui.components.platforms.PlatformView
import ui.theme.AppTheme
import viewmodels.PlatformListViewModel

class MainActivity : AppCompatActivity(), PlatformInterface, NavigationInterface {

    private val platformListViewModel = PlatformListViewModel()
    private val platformInterface = this@MainActivity // Accede a la instancia de la actividad

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    platformListViewModel.onInit()
                    PlatformView(platformInterface, platformInterface, platformListViewModel)
                }
            }
        }
    }

    //Método que se llama al clicar en una plataforma
    override fun onClickPlatform(platform: Platform) {
        val intent = Intent(this,GameListActivity::class.java)
        intent.putExtra(Constant.platformId, platform.id)
        startActivity(intent)
    }

    override fun onClickGame(game: Game) {
        //Nada
    }

    override fun homeRoute() {
        //Nada
    }

    override fun searchRoute() {
        val intent = Intent(this,SearchActivity::class.java)
        startActivity(intent)
    }

    override fun favoriteRoute() {
        val intent = Intent(this,FavoriteActivity::class.java)
        startActivity(intent)
    }

}