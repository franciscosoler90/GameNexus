/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package com.example.fransoler

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
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.Constant
import entity.Platform
import ui.components.PlatformItem
import viewmodels.PlatformListViewModel
import ui.theme.GameJetpackComposeTheme
import ui.theme.containerColor
import ui.theme.primaryColor

class MainActivity : AppCompatActivity() {

    private var page : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GameJetpackComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PlatformList()
                }
            }
        }
    }

    @Composable
    fun PlatformList() {

        val viewModel = PlatformListViewModel()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            backgroundColor = containerColor,
            topBar = {
                TopAppBar(
                    title = { Text(text = "Fran Soler") },
                    actions = {

                        IconButton(onClick = {

                        }) {
                            Icon(imageVector = Icons.Rounded.Search, contentDescription = null)
                        }

                        IconButton(onClick = {

                        }) {
                            Icon(imageVector = Icons.Rounded.Menu, contentDescription = null)
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

                    IconButton(onClick = { updatePrevious(viewModel = viewModel) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = primaryColor.copy(
                                Constant.iconGrayColorAlpha
                            )
                        )
                    }

                    IconButton(onClick = { updateForward(viewModel = viewModel) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = primaryColor.copy(
                                Constant.iconGrayColorAlpha
                            )
                        )
                    }
                }
            }

        ) { padding ->
            LazyColumn(
                modifier = Modifier.padding(padding)
            ) {
                items(viewModel.platformList) { platform ->
                    PlatformItem(platform = platform) { onItemSelected(platform = platform) }
                }
            }
        }
    }


    private fun updateForward(viewModel : PlatformListViewModel){
        if(viewModel.next != null){
            page++
            viewModel.updatePage(page)
        }
    }

    private fun updatePrevious(viewModel : PlatformListViewModel){
        if(page > 1){
            page--
            viewModel.updatePage(page)
        }
    }

    //Método que se llama al clicar
    private fun onItemSelected(platform: Platform){
        val intent = Intent(this,GameList::class.java)
        intent.putExtra(Constant.platformId, platform.id)
        startActivity(intent)
    }

}