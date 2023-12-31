/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components.platforms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import interfaces.PlatformInterface
import viewmodels.PlatformListViewModel

@Composable
fun PlatformList(platformInterface: PlatformInterface, platformListViewModel: PlatformListViewModel, innerPadding: PaddingValues) {

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
            items(platformListViewModel.platformList.sortedBy { it.name }) { plataforma ->
                PlatformItem(platform = plataforma) {
                    platformInterface.onClickPlatform(plataforma)
                }
            }
        }
    }
}