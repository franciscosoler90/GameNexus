/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components

import android.annotation.SuppressLint
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import common.showToast
import entidades.DetailScreenEvent
import interfaces.GameInfoInterface
import viewmodels.GameInfoViewModel
import viewmodels.GameScreenshotsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GameInfo(gameInfoInterface: GameInfoInterface, gameId: Long){

    val gameViewModel = GameInfoViewModel(gameId)
    val screenshotsViewModel = GameScreenshotsViewModel(gameId)

    val context = LocalContext.current

    Scaffold { _ ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy((-30).dp),
            ) {

                GamePoster(game = gameViewModel, gameInfoInterface)

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                        .padding(24.dp)
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = gameViewModel.name,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.weight(1F)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Icon(
                            imageVector = if (gameViewModel.isFavorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                            contentDescription = null,
                            tint = Color.Red,
                            modifier = Modifier
                                .size(32.dp)
                                .padding(top = 4.dp)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = rememberRipple(bounded = false),
                                    onClick = {
                                        gameViewModel.isFavorite = !gameViewModel.isFavorite
                                        gameViewModel.onEvent(
                                            DetailScreenEvent.BookmarkGame(
                                                id = gameViewModel.id,
                                                bookmarked = gameViewModel.isFavorite
                                            )
                                        )
                                        //Favorito
                                        //gameInfoInterface.changeFavorite(gameViewModel.isFavorite)

                                        if (gameViewModel.isFavorite) {
                                            context.showToast("Marcado como favorito")
                                        }else{
                                            context.showToast("Eliminado como favorito")
                                        }

                                    }
                                )
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    GeneralGameInfo(gameInfoViewModel = gameViewModel)

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Descripción",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = gameViewModel.description.ifBlank { "-" },
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.secondary,
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Screenshots(urls = screenshotsViewModel.screenshotsList)

                }
            }
        }
    }
}