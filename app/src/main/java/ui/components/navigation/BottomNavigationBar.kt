/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import common.Constant
import interfaces.NavigationInterface
import navigation.BottomBarDestination

@Composable
fun BottomNavigationBar(
    items: List<BottomBarDestination>,
    modifier: Modifier = Modifier,
    current: String,
    navigationInterface: NavigationInterface
) {

    NavigationBar(modifier = modifier) {
        items.forEach { bottomNavItem ->
            NavigationBarItem(
                enabled = current != bottomNavItem.direction,
                selected = current == bottomNavItem.direction,
                onClick = {

                    when (bottomNavItem) {
                        BottomBarDestination.Home -> {
                            if (current != Constant.homeRoute) {
                                navigationInterface.homeRoute()
                            }
                        }
                        BottomBarDestination.Search -> {
                            if (current != Constant.searchRoute) {
                                navigationInterface.searchRoute()
                            }
                        }
                        BottomBarDestination.Favorite -> {
                            if (current != Constant.favoriteRoute) {
                                navigationInterface.favoriteRoute()
                            }
                        }
                    }
                },
                label = {
                    Text(
                        text = stringResource(bottomNavItem.label),
                        color = if (current == bottomNavItem.direction) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.primary
                    )
                },
                icon = {
                    Image(
                        painter = rememberVectorPainter(image = bottomNavItem.icon),
                        contentDescription = stringResource(bottomNavItem.label),
                        colorFilter = ColorFilter.tint(if (current == bottomNavItem.direction) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.primary),
                        modifier = Modifier.size(26.dp)
                    )
                }
            )
        }
    }
}