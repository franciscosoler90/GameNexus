/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import interfaces.NavigationInterface
import entidades.enums.BottomBarDestination
import entidades.enums.BottomBarState
import ui.components.GameItem
import ui.components.navigation.BottomNavigationBar
import viewmodels.GameSearchViewModel

@Composable
fun FavoriteView(navigationInterface: NavigationInterface) {

    val gameSearchViewModel = viewModel<GameSearchViewModel>()

    gameSearchViewModel.onError = {
        println("Error gameSearchViewModel")
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),

        bottomBar = {
            BottomNavigationBar(
                items = BottomBarDestination.values().toList(),
                current = BottomBarState.FAVORITE,
                navigationInterface = navigationInterface
            )

        }
    ) {innerPadding ->

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
                items(gameSearchViewModel.listGames) { game ->
                    GameItem(game = game) {
                        navigationInterface.onClickGame(game)
                    }
                }
            }
        }
    }
}