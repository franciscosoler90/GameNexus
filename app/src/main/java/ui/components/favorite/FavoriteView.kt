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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseUser
import interfaces.NavigationInterface
import entidades.enums.BottomBarDestination
import entidades.enums.BottomBarState
import ui.components.game.GameItem
import ui.components.navigation.BottomNavigationBar
import viewmodels.GameFavoriteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteView(
    currentUser : FirebaseUser?,
    navigationInterface: NavigationInterface,
    gameFavoriteViewModel : GameFavoriteViewModel) {

    LaunchedEffect(Unit) {
        if (currentUser != null) {
            gameFavoriteViewModel.onInit(currentUser.uid)
        }
    }

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.fillMaxSize(),

        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    if (currentUser != null) {
                        Text(
                            "Favoritos de " + currentUser.displayName,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Rounded.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },

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
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxSize()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(gameFavoriteViewModel.favoriteList.value.sortedBy { it.name }) { game ->
                    GameItem(game = game) {
                        navigationInterface.onClickGame(game)
                    }
                }
            }
        }
    }
}