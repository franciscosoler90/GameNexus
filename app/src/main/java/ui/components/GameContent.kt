/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import common.showToast
import entidades.ConverterDate
import entidades.DetailScreenEvent
import entidades.convertDateTo
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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter",
    "UnusedMaterial3ScaffoldPaddingParameter"
)
@Composable
fun ShowGameInfo(gameInfoInterface: GameInfoInterface, game: Long){

    val gameViewModel = GameInfoViewModel(game)
    val screenshotsViewModel = GameScreenshotsViewModel(game)

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

                                            if (gameViewModel.isFavorite) context.showToast("Marcado como favorito")
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

@Composable
fun GamePoster(
    game: GameInfoViewModel,
    gameInfoInterface: GameInfoInterface)
{
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        NetworkImage(
            url = game.background,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.2F))
        )
        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 18.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                        onClick = {
                            //Volver atras
                            gameInfoInterface.back()
                        }
                    )
            )
            Icon(
                imageVector = Icons.Rounded.Share,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                        onClick = {
                            //Compartir
                            gameInfoInterface.compartir(game)

                        }
                    )
            )
        }

    }
}


@Composable
fun GeneralGameInfo(
    gameInfoViewModel: GameInfoViewModel,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(36.dp)
        ) {
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
                        text = if (gameInfoViewModel.metacritic != 0) gameInfoViewModel.metacritic.toString() else "-",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            Column {
                Text(
                    text = "Puntuación",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                RatingBar(
                    rating = gameInfoViewModel.rating,
                    modifier = Modifier
                        .height(16.dp)
                )
            }
        }
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
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = gameInfoViewModel.released.convertDateTo(ConverterDate.FULL_DATE),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Column {
                Text(
                    text = "Géneros",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                TagGroup(tag = gameInfoViewModel.genres)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagGroup(
    tag: List<String>,
    modifier: Modifier = Modifier,
    isLimited: Boolean = false)
{
    val limitedGenres = remember(tag) {
        if (tag.size >= 3) 3 else tag.size
    }

    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(if (isLimited) limitedGenres else tag.size) {
            TagChip(name = tag[it])
        }
    }
}

@Composable
fun TagChip(name: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(100.dp),
        color = MaterialTheme.colorScheme.tertiary
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onTertiary,
            modifier = Modifier.padding(vertical = 3.dp, horizontal = 12.dp)
        )
    }
}

@Composable
fun RatingBar(
    rating: String,
    modifier: Modifier = Modifier,
    color: Color = Yellow
) {
    if(rating.isNotEmpty()){

        val newRating = rating.toFloat()

        Row(modifier = modifier.wrapContentSize()) {
            (1..5).forEach { step ->
                val stepRating = when {
                    newRating > step -> 1f
                    step.rem(newRating) < 1 -> newRating - (step - 1f)
                    else -> 0f
                }
                RatingStar(stepRating, color)
            }
        }
    }
}

@Composable
private fun RatingStar(
    rating: Float,
    ratingColor: Color = Yellow,
    backgroundColor: Color = MaterialTheme.colorScheme.secondary
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxHeight()
            .aspectRatio(1f)
            .clip(starShape)
    ) {
        Canvas(modifier = Modifier.size(maxHeight)) {
            drawRect(
                brush = SolidColor(backgroundColor),
                size = Size(
                    height = size.height * 1.4f,
                    width = size.width * 1.4f
                ),
                topLeft = Offset(
                    x = -(size.width * 0.1f),
                    y = -(size.height * 0.1f)
                )
            )
            if (rating > 0) {
                drawRect(
                    brush = SolidColor(ratingColor),
                    size = Size(
                        height = size.height * 1.1f,
                        width = size.width * rating
                    )
                )
            }
        }
    }
}

private val starShape = GenericShape { size, _ ->
    addPath(starPath(size.height))
}

private val starPath = { size: Float ->
    Path().apply {
        val outerRadius: Float = size / 1.8f
        val innerRadius: Double = outerRadius / 2.5
        var rot: Double = Math.PI / 2 * 3
        val cx: Float = size / 2
        val cy: Float = size / 20 * 11
        var x: Float
        var y: Float
        val step = Math.PI / 5

        moveTo(cx, cy - outerRadius)
        repeat(5) {
            x = (cx + kotlin.math.cos(rot) * outerRadius).toFloat()
            y = (cy + kotlin.math.sin(rot) * outerRadius).toFloat()
            lineTo(x, y)
            rot += step

            x = (cx + kotlin.math.cos(rot) * innerRadius).toFloat()
            y = (cy + kotlin.math.sin(rot) * innerRadius).toFloat()
            lineTo(x, y)
            rot += step
        }
        close()
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
fun Tags(text: String, list : List<String>) {

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {

        TextElement(text = text)
        Spacer(modifier = Modifier.padding(4.dp))
        TagsRow(list = list.map { it })
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
        if (metacritic <= 40) Color.Red else if (metacritic <= 70) Yellow else Color.Green

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