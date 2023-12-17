/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import entidades.Developer
import entidades.Genre
import entidades.Platforms
import entidades.Publisher
import interfaces.GameInfoInterface
import interfaces.GameListInterface
import viewmodels.GameInfoViewModel
import viewmodels.GameListViewModel
import viewmodels.GameScreenshotsViewModel
import viewmodels.PlatformInfoViewModel

@Composable
fun showGameList(gameListCallbacks: GameListInterface, platformId: Int, page: Int){

    val viewModel1 = GameListViewModel(platformId,page)
    val viewModel2 = PlatformInfoViewModel(platformId)

    Scaffold(
        topBar = {
            searchBar(gameListCallbacks, viewModel2.platformName)
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(MaterialTheme.colorScheme.background),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                IconButton(onClick = {
                    gameListCallbacks.updatePrevious(viewModel1)
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                IconButton(onClick = {
                    gameListCallbacks.updateForward(viewModel1)
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        },

        ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding)
        ) {
            items(viewModel1.gameList) { game ->
                GameItem(game = game) {
                    gameListCallbacks.onGameClicked(game)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter",
    "UnusedMaterial3ScaffoldPaddingParameter"
)
@Composable
fun ShowGameInfo(gameInfoInterface: GameInfoInterface, gameId: Int, favorite : Boolean){

    var favoriteState by remember { mutableStateOf(favorite) }
    val scrollState = rememberScrollState()

    val gameViewModel = GameInfoViewModel(gameId)
    val screenshotsViewModel = GameScreenshotsViewModel(gameId)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        gameInfoInterface.back()
                    }) {
                        Icon(imageVector = Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Volver atrás")
                    }
                },
                title = { Text(text = "") },
                actions = {

                    IconButton(onClick = {
                        favoriteState = !favoriteState
                        gameInfoInterface.changeFavorite(favoriteState)
                    }) {
                        Icon(imageVector =
                        if(favoriteState){
                            Icons.Rounded.Favorite
                        }else{
                            Icons.Rounded.FavoriteBorder
                        },
                            contentDescription = null, tint = Color.Red)
                    }

                    IconButton(onClick = {
                        gameInfoInterface.compartir(gameViewModel)
                    }) {
                        Icon(imageVector = Icons.Rounded.Share, contentDescription = gameViewModel.name)
                    }

                }
            )
        }

    ) {

        Column(modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(scrollState)) {

                ImageSlider(screenshotsViewModel = screenshotsViewModel)

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 0.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.background,
                    ),
                ) {
                    Row(modifier = Modifier.padding(16.dp)) {
                        GameContent(game = gameViewModel)
                    }
                }

            }
        }
    }

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
                    TagsGenres("Géneros", list = game.genres)
                }

                if(game.platforms.isNotEmpty()){
                    Spacer(modifier = Modifier.height(8.dp))
                    TagsPlatforms("Plataformas", list = game.platforms)
                }

                if(game.publishers.isNotEmpty()){
                    Spacer(modifier = Modifier.height(8.dp))
                    TagsPublishers("Distribuidores", list = game.publishers)
                }

                if(game.developers.isNotEmpty()){
                    Spacer(modifier = Modifier.height(8.dp))
                    TagsDevelopers("Desarrolladores", list = game.developers)
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

@Composable
fun DateComponent(title: String, date: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {

        TextElement(text = title)

        Text(
            text = date,
            style = MaterialTheme.typography.titleSmall
        )
    }
}


@Composable
fun TagsPlatforms(text: String, list : List<Platforms>) {

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {

        TextElement(text = text)
        Spacer(modifier = Modifier.padding(4.dp))
        TagsRow(list = list.map { it.platform.name })
    }
}

@Composable
fun TagsGenres(text: String, list : List<Genre>) {

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {

        TextElement(text = text)
        Spacer(modifier = Modifier.padding(4.dp))
        TagsRow(list = list.map { it.name })

    }
}

@Composable
fun TagsPublishers(text: String, list : List<Publisher>) {

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {

        TextElement(text = text)
        Spacer(modifier = Modifier.padding(4.dp))
        TagsRow(list = list.map { it.name })

    }
}

@Composable
fun TagsDevelopers(text: String, list : List<Developer>) {

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {

        TextElement(text = text)
        Spacer(modifier = Modifier.padding(4.dp))
        TagsRow(list = list.map { it.name })

    }
}

@Composable
fun TextElement(text : String) {

    Text(
        text = text,
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.secondary
    )
}

@Composable
fun TagsRow(list : List<String>) {

    // Utilizar LazyRow para organizar los Tags en filas
    LazyRow(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        items(list) { text ->
            TagElement(text = text)
        }
    }

}

@Composable
fun TagElement(text : String) {

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