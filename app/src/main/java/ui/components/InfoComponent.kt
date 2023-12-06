/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import entity.Genre
import entity.Platforms

@Composable
fun DateComponent(title: String, description: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.secondary)
        Text(
            text = description,
            style = MaterialTheme.typography.titleSmall)
    }
}

@Composable
fun TagsPlatforms(list : List<Platforms>) {

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {

        Text(
            text = "Plataformas",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.secondary)
        Spacer(modifier = Modifier.padding(4.dp))

        // Utilizar LazyRow para organizar los Tags en filas
        LazyRow(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            items(list) { platform ->
                Tag(text = platform.platform.name)
            }
        }
    }
}

@Composable
fun TagsGenres(list : List<Genre>) {

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {

        Text(
            text = "Géneros",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.padding(4.dp))

        // Utilizar LazyRow para organizar los Tags en filas
        LazyRow(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            items(list) { genre ->
                Tag(text = genre.name)
            }
        }
    }
}

@Composable
fun Tag(text : String) {
    Surface(
        modifier = Modifier.padding(horizontal = 0.dp),
        shadowElevation = 2.dp,
        shape = CircleShape,
        color = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            color = MaterialTheme.colorScheme.onSecondaryContainer)
        }

    Spacer(modifier = Modifier.padding(4.dp))
}

@Composable
fun MetacriticCard(metacritic : Int){

        val ratingColor =
            if (metacritic <= 40) Color.Red else if (metacritic <= 70) Color.Yellow else Color.Green

        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            ),
            border = BorderStroke(1.dp, ratingColor),
            shape = RoundedCornerShape(6.dp),
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp),
                text = metacritic.toString(),
                fontSize = 18.sp,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 1,
                textAlign = TextAlign.Center,
                color = ratingColor
            )
        }

}