/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import interfaces.NavigationInterface
import entidades.enums.BottomBarDestination
import entidades.enums.BottomBarState
import ui.components.GameItem
import ui.components.navigation.BottomNavigationBar
import viewmodels.GameSearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(navigationInterface: NavigationInterface) {

    val gameSearchViewModel = viewModel<GameSearchViewModel>()

    gameSearchViewModel.onError = {
        println("Error gameSearchViewModel")
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {

            var query by remember { mutableStateOf("") }
            var active by remember { mutableStateOf(false) }

            SearchBar(
                query = query,
                onQueryChange = {
                    query = it
                    gameSearchViewModel.searchGames(query)
                },
                onSearch = {active = false},
                active = active,
                onActiveChange = {active = it},
                placeholder = { Text(text = "Buscar juegos") },
                leadingIcon = {
                    IconButton(onClick = {
                        active = false
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            tint = MaterialTheme.colorScheme.onSurface,
                            contentDescription = null
                        )
                    }
                },
                trailingIcon = {
                    IconButton(onClick = {
                        gameSearchViewModel.searchGames(query)
                    }, enabled = query.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            tint = MaterialTheme.colorScheme.onSurface,
                            contentDescription = null
                        )
                    }
                },
                content = {},
                tonalElevation = 0.dp,
                modifier = Modifier.fillMaxWidth(),
                shape = SearchBarDefaults.dockedShape,
                colors = SearchBarDefaults.colors()
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