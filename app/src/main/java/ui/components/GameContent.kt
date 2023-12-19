/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import entidades.MetacriticCard
import viewmodels.GameInfoViewModel

@Composable
fun GameContent(game: GameInfoViewModel) {

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(8.dp)) {

                Text(
                    text = game.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(8.dp))

                if(game.released.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    DateComponent(
                        title = "Fecha de lanzamiento",
                        date = game.released
                    )
                }

                if(game.genres.isNotEmpty()){
                    Spacer(modifier = Modifier.height(8.dp))
                    Tags("Géneros", list = game.genres)
                }

                if(game.platforms.isNotEmpty()){
                    Spacer(modifier = Modifier.height(8.dp))
                    Tags("Plataformas", list = game.platforms)
                }

                if(game.publishers.isNotEmpty()){
                    Spacer(modifier = Modifier.height(8.dp))
                    Tags("Distribuidores", list = game.publishers)
                }

                if(game.developers.isNotEmpty()){
                    Spacer(modifier = Modifier.height(8.dp))
                    Tags("Desarrolladores", list = game.developers)
                }

                if(game.metacritic > 0) {
                    Spacer(modifier = Modifier.height(8.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = "Metascore",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.secondary)
                        Spacer(modifier = Modifier.height(8.dp))
                        MetacriticCard(metacritic = game.metacritic)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Acerca de",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = game.description,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}