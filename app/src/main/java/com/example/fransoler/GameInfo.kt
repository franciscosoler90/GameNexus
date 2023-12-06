/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package com.example.fransoler

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import common.Constant
import ui.components.GameContent
import ui.components.ImageSlider
import ui.theme.AppTheme
import viewmodels.GameInfoViewModel
import viewmodels.GameScreenshotsViewModel

class GameInfo : AppCompatActivity() {

    //variables globales
    private var platformId: Int = 0
    private var page : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        platformId = intent.getIntExtra(Constant.platformId,0)
        page = intent.getIntExtra(Constant.page,1)

        val favorite = false

        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ShowGameInfo(intent.getIntExtra(Constant.gameId,1), favorite)
                }
            }
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    private fun ShowGameInfo(gameId: Int, favorite : Boolean){

        var favoriteState by remember { mutableStateOf(favorite) }
        val scrollState = rememberScrollState()

        val gameViewModel = GameInfoViewModel(gameId)
        val screenshotsViewModel = GameScreenshotsViewModel(gameId)

        Scaffold(
            backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        back()
                    }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver atrás")
                    }
                },
                title = { Text(text = "") },
                actions = {

                    IconButton(onClick = {
                        favoriteState = !favoriteState
                    }) {
                        Icon(imageVector =
                            if(favoriteState){
                                Icons.Rounded.Favorite
                            }else{
                                Icons.Rounded.FavoriteBorder
                            },
                            contentDescription = null, tint = Color.Red)
                    }

                    IconButton(onClick = {
                        compartir(game = gameViewModel)
                    }) {
                        Icon(imageVector = Icons.Rounded.Share, contentDescription = null)
                    }


                }
            )
        }

        ) {

            Column(modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)) {

                ImageSlider(screenshotsViewModel = screenshotsViewModel)

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.background,
                    elevation = 0.dp
                ) {
                    Row(modifier = Modifier.padding(16.dp)) {
                        GameContent(game = gameViewModel)
                    }
                }

            }
        }
    }

    private fun back(){
        //Volver atrás
        val intent = Intent(this,GameList::class.java)
        intent.putExtra(Constant.platformId, platformId)
        intent.putExtra(Constant.page, page)
        startActivity(intent)
    }


    private fun compartir(game : GameInfoViewModel) {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_SUBJECT, "Aplicación Francisco José Soler Conchello")
        intent.putExtra(Intent.EXTRA_TEXT, "https://rawg.io/games/" + game.slug)
        intent.type = "text/plain"
        startActivity(intent)
    }

}