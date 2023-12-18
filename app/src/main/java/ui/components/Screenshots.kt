/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.request.ImageRequest
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun Screenshots(
    urls: List<String>,
    modifier: Modifier = Modifier)
{
    Column(modifier = modifier) {

        Spacer(modifier = Modifier.padding(8.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(
                items = urls,
                key = { it }
            ) {
                NetworkImage(
                    url = it,
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .height(200.dp)
                )
            }
        }
    }
}

@Composable
fun NetworkImage(
    url: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = "",
        contentScale = contentScale,
        modifier = modifier
    )
}