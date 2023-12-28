/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import navigation.BottomBarDestination
import navigation.BottomBarState

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
            println(currentTab)
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
            println(currentTab)
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
            println(currentTab)
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


@Composable
fun BottomNavigationBar(
    navController: NavController,
    items: List<BottomBarDestination>,
    modifier: Modifier = Modifier,
    onItemClick: (BottomBarDestination) -> Unit
) {

    val currentDestination = navController.currentDestination

    NavigationBar(modifier = modifier) {
        items.forEach { bottomNavItem ->
            NavigationBarItem(
                selected = currentDestination == bottomNavItem.direction,
                onClick = {
                    if (bottomNavItem.direction != currentDestination) {
                        onItemClick(bottomNavItem)
                    }
                },
                label = {
                    Text(
                        text = stringResource(bottomNavItem.label),
                        color = if (currentDestination == bottomNavItem.direction) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                    )
                },
                icon = {
                    Image(
                        painter = rememberVectorPainter(image = bottomNavItem.icon),
                        contentDescription = stringResource(bottomNavItem.label),
                        colorFilter = ColorFilter.tint(if (currentDestination == bottomNavItem.direction) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary),
                        modifier = Modifier.size(26.dp)
                    )
                }
            )
        }
    }
}
