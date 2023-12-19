/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import interfaces.GameListInterface
import viewmodels.GameListViewModel
import viewmodels.PlatformInfoViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GameList(gameListCallbacks: GameListInterface, platformId: Int, page: Int) {

    val gameListViewModel = GameListViewModel(platformId, page)
    val platformViewModel = PlatformInfoViewModel(platformId)

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp),
        topBar = {
            searchBar(gameListCallbacks, platformViewModel.platformName)
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(MaterialTheme.colorScheme.background),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                IconButton(onClick = {
                    gameListCallbacks.updatePrevious(gameListViewModel)
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                IconButton(onClick = {
                    gameListCallbacks.updateForward(gameListViewModel)
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    ) {
        // Column que contiene el LazyColumn
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp, bottom = 60.dp) // Ajusta el espacio superior e inferior
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 24.dp), // Ajusta el espacio vertical entre elementos
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(gameListViewModel.gameList) { game ->
                    GameItem(gameViewModel = game) {
                        gameListCallbacks.onGameClicked(game)
                    }
                }
            }
        }
    }
}
