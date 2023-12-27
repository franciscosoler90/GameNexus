/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.unit.dp
import entidades.ConverterDate
import entidades.Game
import utilidades.convertDateTo

@Composable
fun GameItem(
    game: Game,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit)
{
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable(onClick = onItemClick)
    ) {

        NetworkImage(
            url = game.background_image,
            modifier = Modifier
                .fillMaxHeight()
                .width(85.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = game.name,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(4.dp))

            if(game.rating > 0){
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        contentDescription = null,
                        tint = Yellow,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "${game.rating}/5",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            if(game.genres.isNotEmpty()){
                Spacer(modifier = Modifier.height(4.dp))
                TagGroup(tag = game.genres.flatMap { genres -> listOf(genres.name) }, modifier, true)
                Spacer(modifier = Modifier.height(4.dp))
            }

            if(!game.released.isNullOrBlank()){

                game.released.convertDateTo(ConverterDate.FULL_DATE)?.let {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Icon(
                        imageVector = Icons.Rounded.DateRange,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(12.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp).height(8.dp))

                        Text(
                            text = it,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        }
    }
}
