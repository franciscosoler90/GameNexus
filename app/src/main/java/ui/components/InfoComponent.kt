/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import entity.Genre
import entity.Platforms

@Composable
fun InfoComponent(title: String, description: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(text = title, style = MaterialTheme.typography.subtitle2)
        Text(text = description, style = MaterialTheme.typography.body2)
    }
}

@Composable
fun TagsPlatforms(list : List<Platforms>) {

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {

        Text(text = "Platforms", style = MaterialTheme.typography.subtitle2)
        Spacer(modifier = Modifier.padding(4.dp))

        Row(modifier = Modifier.fillMaxSize()) {
            list.forEach { platform ->
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

        Text(text = "Genres", style = MaterialTheme.typography.subtitle2)
        Spacer(modifier = Modifier.padding(4.dp))

        Row(modifier = Modifier.fillMaxSize()) {

            list.forEach { genre ->
                Tag(text = genre.name)
            }

        }
    }
}

@Composable
fun Tag(text : String) {
    Surface(
        modifier = Modifier.padding(horizontal = 0.dp),
        elevation = 8.dp,
        shape = CircleShape,
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.body2,
            maxLines = 2,
            color = Color.White)
        }

    Spacer(modifier = Modifier.padding(4.dp))
}

@Composable
fun MetacriticCard(metacritic : Int){

        val ratingColor =
            if (metacritic <= 40) Color.Red else if (metacritic <= 70) Color.Yellow else Color.Green

        Card(
            elevation = 8.dp,
            border = BorderStroke(1.dp, ratingColor),
            shape = RoundedCornerShape(6.dp),
            backgroundColor = Color.Transparent
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp),
                text = metacritic.toString(),
                fontSize = 18.sp,
                style = MaterialTheme.typography.h5,
                maxLines = 1,
                textAlign = TextAlign.Center,
                color = ratingColor
            )
        }

}