/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components.platforms

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import navigation.BottomBarState
import interfaces.PlatformInterface
import navigation.BottomBarDestination
import ui.components.navigation.BottomNavigationBar
import viewmodels.PlatformListViewModel

@Composable
fun PlatformList(plataformaCallbacks: PlatformInterface, current: BottomBarState) {
    val platformListViewModel = PlatformListViewModel()

    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                items = BottomBarDestination.values().toList(),
                onItemClick = { destination ->

                    println(destination)

                    when (destination) {
                        BottomBarDestination.Home -> {

                        }

                        BottomBarDestination.Search -> {

                        }

                        BottomBarDestination.Favorite -> {

                        }
                    }
                }
            )
        }
    ) { innerPadding ->

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(innerPadding)
        ) {
            items(platformListViewModel.platformList) { plataforma ->
                PlataformaItem(platform = plataforma) {
                    plataformaCallbacks.onPlatformClicked(plataforma)
                }
            }
        }
    }
}

