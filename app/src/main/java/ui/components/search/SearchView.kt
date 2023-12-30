/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components.search

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import interfaces.NavigationInterface
import entidades.enums.BottomBarDestination
import entidades.enums.BottomBarState
import ui.components.game.GameItem
import ui.components.navigation.BottomNavigationBar
import viewmodels.GameSearchViewModel

@Composable
fun SearchView(navigationInterface: NavigationInterface) {

    val gameSearchViewModel = viewModel<GameSearchViewModel>()
    val controller = LocalSoftwareKeyboardController.current

    gameSearchViewModel.onError = {
        println("Error gameSearchViewModel")
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {

            var query by remember { mutableStateOf("") }
            var active by remember { mutableStateOf(false) }

            val searchPrecise = true
            val searchExact = false

            SearchAppBar(
                text = query,
                onTextChange = {
                        newText -> query = newText
                        gameSearchViewModel.searchGames(newText, searchPrecise, searchExact)
                               },
                onCloseClicked = {
                    active = false
                    query = ""
                    controller?.hide() },
                onSearchClicked = { _ ->
                    active = false
                    controller?.hide()
                    if(query.isNotEmpty()) {
                        gameSearchViewModel.searchGames(query, searchPrecise, searchExact)
                    }
                }
            )
        },

        bottomBar = {
            BottomNavigationBar(
                items = BottomBarDestination.values().toList(),
                current = BottomBarState.SEARCH,
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