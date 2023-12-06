/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package com.example.fransoler

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.Constant
import entity.Game
import ui.components.GameItem
import viewmodels.GameListViewModel
import viewmodels.PlatformInfoViewModel
import ui.theme.GameJetpackComposeTheme

class GameList : AppCompatActivity() {

    private var platformId: Int = 0
    private var page : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        platformId = intent.getIntExtra(Constant.platformId,0)
        page = intent.getIntExtra(Constant.page,1)

        setContent {
            GameJetpackComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GameList()
                }
            }
        }
    }

    @SuppressLint("NotConstructor")
    @Composable
    private fun GameList(){

        val viewModel1 = GameListViewModel(platformId,page)
        val viewModel2 = PlatformInfoViewModel(platformId)

        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = {
                            val intent = Intent(this,MainActivity::class.java)
                            startActivity(intent)
                        }) {
                            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver atrás")
                        }
                    },
                    title = {
                        Text(text = viewModel2.platformName)
                    },
                    actions = {


                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null
                            )
                        }

                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = "Ver más"
                            )
                        }
                    }
                )
            },
            bottomBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(MaterialTheme.colors.background),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                    IconButton(onClick = { updatePrevious(viewModel = viewModel1) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = MaterialTheme.colors.primary
                        )
                    }

                    IconButton(onClick = { updateForward(viewModel = viewModel1) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = MaterialTheme.colors.primary
                        )
                    }
                }
            },

        ) { padding ->
            LazyColumn(
                modifier = Modifier.padding(padding)
            ) {
                items(viewModel1.gameList) { game ->
                    GameItem(game = game) { onItemSelected(game = game) }
                }
            }
        }
    }

    private fun updateForward(viewModel : GameListViewModel){
        if(viewModel.next != null){
            page++
            viewModel.updatePage(page)
        }
    }

    private fun updatePrevious(viewModel : GameListViewModel){
        if(page > 1){
            page--
            viewModel.updatePage(page)
        }
    }

    //Método que se llama al clicar
    private fun onItemSelected(game: Game){
        val intent = Intent(this,GameInfo::class.java)
        intent.putExtra(Constant.gameId, game.id)
        intent.putExtra(Constant.platformId, platformId)
        intent.putExtra(Constant.page, page)
        startActivity(intent)
    }

}