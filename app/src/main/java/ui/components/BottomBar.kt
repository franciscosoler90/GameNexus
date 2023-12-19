/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import entidades.BottomBarState

@Composable
fun BottomBar(initialState: BottomBarState, onTabSelected: (BottomBarState) -> Unit) {
    var currentTab by remember { mutableStateOf(initialState) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        IconButton(onClick = {
            currentTab = BottomBarState.HOME
            onTabSelected.invoke(currentTab)
        }) {
            Icon(
                imageVector = Icons.Rounded.Home,
                contentDescription = null,
                tint = if (currentTab == BottomBarState.HOME)
                    MaterialTheme.colorScheme.onBackground
                else
                    MaterialTheme.colorScheme.secondary
            )
        }

        IconButton(onClick = {
            currentTab = BottomBarState.SEARCH
            onTabSelected.invoke(currentTab)
        }) {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = null,
                tint = if (currentTab == BottomBarState.SEARCH)
                    MaterialTheme.colorScheme.onBackground
                else
                    MaterialTheme.colorScheme.secondary
            )
        }

        IconButton(onClick = {
            currentTab = BottomBarState.FAVORITE
            onTabSelected.invoke(currentTab)
        }) {
            Icon(
                imageVector = Icons.Rounded.Favorite,
                contentDescription = null,
                tint = if (currentTab == BottomBarState.FAVORITE)
                    MaterialTheme.colorScheme.onBackground
                else
                    MaterialTheme.colorScheme.secondary
            )
        }
    }
}
