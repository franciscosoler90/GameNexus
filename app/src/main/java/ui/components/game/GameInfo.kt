/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components.game

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import interfaces.GameInterface
import viewmodels.GameInfoViewModel


@Composable
fun GameInfo(
    userId: String?,
    gameId: Long,
    gameInfoViewModel: GameInfoViewModel,
    gameInterface: GameInterface
){

    LaunchedEffect(key1 = Unit, block = {
        if (userId != null) {
            gameInfoViewModel.onInit(gameId, userId)
        }
    })

    val state by gameInfoViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        state.game?.let { game ->
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy((-30).dp),
            ) {

                GamePoster(game = game, gameInterface)

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                        .background(MaterialTheme.colorScheme.background)
                        .padding(24.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = game.name,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.weight(1F)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Icon(
                            imageVector = if (state.isFavoriteGame) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                            contentDescription = null,
                            tint = if (state.isFavoriteGame) Color.Red else Color.White,
                            modifier = Modifier
                                .size(32.dp)
                                .padding(top = 4.dp)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = rememberRipple(bounded = false),
                                    onClick = {
                                        //Favorito
                                        gameInfoViewModel.toggleFavorite()
                                        gameInterface.onToogleFavorite(!state.isFavoriteGame)
                                    }
                                )
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    GeneralGameInfo(game = game)

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Descripción",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = gameInfoViewModel.cleanDescription,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                    )

                    if(gameInfoViewModel.listScreenshots.isNotEmpty()){
                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = "Imágenes",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        Screenshots(urls = gameInfoViewModel.listScreenshots)
                    }

                }
            }
        }
    }
}