/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components.platforms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import entidades.Platform
import ui.components.game.NetworkImage

@Composable
fun PlatformItem(
    platform: Platform,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(85.dp)
            .clickable(onClick = onItemClick)
    ) {

        NetworkImage(
            url = platform.image_background,
            modifier = Modifier
                .fillMaxHeight()
                .width(85.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth().align(Alignment.CenterVertically)
        ) {
            Text(
                text = platform.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 2
            )
        }
    }
}