/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components.platforms

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import interfaces.PlatformInterface
import viewmodels.PlatformListViewModel

@Composable
fun PlatformGrid(platformInterface: PlatformInterface, platformListViewModel: PlatformListViewModel, paddingValues: PaddingValues) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(paddingValues)
    ) {
        items(platformListViewModel.platformList) { plataforma ->
            PlataformaItem(platform = plataforma) {
                platformInterface.onPlatformClicked(plataforma)
            }
        }
    }

}