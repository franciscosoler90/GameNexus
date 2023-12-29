/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components.platforms

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common.Constant
import interfaces.NavigationInterface
import interfaces.PlatformInterface
import navigation.BottomBarDestination
import ui.components.navigation.BottomNavigationBar
import viewmodels.PlatformListViewModel

@Composable
fun PlatformView(platformInterface: PlatformInterface, navigationInterface: NavigationInterface, platformListViewModel: PlatformListViewModel) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {

            BottomNavigationBar(
                items = BottomBarDestination.values().toList(),
                current = Constant.homeRoute,
                navigationInterface = navigationInterface
            )

        }
    ) { innerPadding ->
        PlatformGrid(platformInterface = platformInterface, platformListViewModel, paddingValues = innerPadding)
    }
}