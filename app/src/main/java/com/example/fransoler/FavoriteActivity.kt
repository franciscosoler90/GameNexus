/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package com.example.fransoler

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import entidades.Game
import entidades.ParentPlatform
import interfaces.NavigationInterface
import ui.components.favorite.FavoriteView
import ui.theme.AppTheme

class FavoriteActivity : ComponentActivity(), NavigationInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    FavoriteView(navigationInterface = this@FavoriteActivity)
                }
            }
        }
    }

    override fun homeRoute() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    override fun searchRoute() {
        val intent = Intent(this,SearchActivity::class.java)
        startActivity(intent)
    }

    override fun favoriteRoute() {
        //Nada
    }

    override fun onClickPlatform(platform: ParentPlatform.Platform) {
        //Nada
    }

    override fun onClickGame(game: Game) {
        //Nada
    }
}