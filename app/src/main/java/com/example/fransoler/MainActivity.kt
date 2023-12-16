/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package com.example.fransoler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common.Constant
import entidades.Platform
import ui.components.PlataformaItem
import viewmodels.PlatformListViewModel
import ui.theme.AppTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PlatformList()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun PlatformList() {

        val viewModel = PlatformListViewModel()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(text = "Plataformas") },
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

        ) { padding ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // Cambia el número de columnas
                modifier = Modifier.padding(padding)
            ) {
                items(viewModel.platformList) { plataforma ->
                    PlataformaItem(platform = plataforma) { onItemSelected(platform = plataforma) }
                }
            }
        }
    }

    //Método que se llama al clicar
    private fun onItemSelected(platform: Platform){
        val intent = Intent(this,GameList::class.java)
        intent.putExtra(Constant.platformId, platform.id)
        startActivity(intent)
    }

}