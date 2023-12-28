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
import entidades.BottomBarState
import entidades.ParentPlatform
import interfaces.PlatformInterface
import ui.components.PlatformList
import ui.theme.AppTheme

class MainActivity : AppCompatActivity(), PlatformInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val plataformaInterface = this@MainActivity // Accede a la instancia de la actividad
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PlatformList(plataformaInterface, BottomBarState.HOME)
                }
            }
        }
    }

    //Método que se llama al clicar
    override fun onPlatformClicked(platform: ParentPlatform.Platform) {
        val intent = Intent(this,GameListActivity::class.java)
        intent.putExtra(Constant.platformId, platform.id)
        startActivity(intent)
    }

}