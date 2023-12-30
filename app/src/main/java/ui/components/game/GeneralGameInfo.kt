/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import entidades.enums.ConverterDate
import entidades.Game
import utilidades.convertDateTo

@Composable
fun GeneralGameInfo(
    game : Game,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(36.dp)
        ) {

            if(game.metacritic > 0) {
                Column {
                    Text(
                        text = "Metascore",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.primary)
                    ) {
                        Text(
                            text = game.metacritic.toString(),
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }

            if(game.rating > 0) {
                Column {
                    Text(
                        text = "Puntuación",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    RatingBar(
                        rating = game.rating.toString(),
                        modifier = Modifier
                            .height(16.dp)
                    )
                }
            }

        }

        if(game.released?.isNotBlank() == true){
            game.released.convertDateTo(ConverterDate.FULL_DATE)?.let {
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(40.dp)
                ) {
                    Column {
                        Text(
                            text = "Fecha de lanzamiento",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        game.released.convertDateTo(ConverterDate.FULL_DATE)?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                }
            }
        }

        val genreNames = game.genres.map { it.name }.toList().filterNotNull()
        val platformNames = game.platforms.map { it.platform.name }.toList()

        if(genreNames.isNotEmpty()){
            Column {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Géneros",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                TagGroup(tag = genreNames, modifier, false)
            }
        }

        if(platformNames.isNotEmpty()){
            Column {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Plataformas",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                TagGroup(tag = platformNames, modifier, false)
            }
        }
    }
}