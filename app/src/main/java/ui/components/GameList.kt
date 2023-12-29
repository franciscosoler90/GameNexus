/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import interfaces.GameInterface
import ui.components.search.searchBar
import viewmodels.GameListViewModel
import viewmodels.PlatformInfoViewModel

@Composable
fun GameList(
    gameInterface: GameInterface,
    platformId: Int, currentPage: Int
) {

    val gameListViewModel = GameListViewModel(platformId,currentPage)
    val platformViewModel = viewModel<PlatformInfoViewModel>()

    // Cargar los juegos favoritos al inicio
    LaunchedEffect(Unit) {
        platformViewModel.onInit(platformId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            searchBar(gameInterface, platformViewModel.platform.name)
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
                    gameInterface.updatePrevious(gameListViewModel)
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                IconButton(onClick = {
                    gameInterface.updateForward(gameListViewModel)
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxSize()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(gameListViewModel.gameList) { game ->
                    GameItem(game = game) {
                        gameInterface.onClickGame(game)
                    }
                }
            }
        }
    }
}
