/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package entidades

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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