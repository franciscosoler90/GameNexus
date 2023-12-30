/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components.game

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
import entidades.enums.BottomGameBarDestination
import interfaces.GameInterface
import viewmodels.GameListViewModel

@Composable
fun BottomGameNavigationBar(
    items: List<BottomGameBarDestination>,
    modifier: Modifier = Modifier,
    gameInterface: GameInterface,
    gameListViewModel: GameListViewModel
) {

    NavigationBar(modifier = modifier) {
        items.forEach { bottomNavItem ->
            NavigationBarItem(
                selected = false,
                onClick = {

                    when (bottomNavItem) {
                        BottomGameBarDestination.Back -> {
                            gameInterface.updatePrevious(gameListViewModel)
                        }
                        BottomGameBarDestination.Forward -> {
                            gameInterface.updateForward(gameListViewModel)
                        }

                    }
                },
                label = {
                    Text(
                        text = stringResource(bottomNavItem.label),
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                icon = {
                    Image(
                        painter = rememberVectorPainter(image = bottomNavItem.icon),
                        contentDescription = stringResource(bottomNavItem.label),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                        modifier = Modifier.size(26.dp)
                    )
                }
            )
        }
    }
}