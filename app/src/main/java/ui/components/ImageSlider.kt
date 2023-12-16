/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import viewmodels.GameScreenshotsViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSlider(screenshotsViewModel: GameScreenshotsViewModel) {

    val pagerState = rememberPagerState(initialPage = 0, 0.0f
    ) { screenshotsViewModel.screenShots.size }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Column {

            SliderView(pagerState, screenshotsViewModel)
            DotsIndicator(
                totalDots = screenshotsViewModel.screenShots.size,
                selectedIndex = pagerState.currentPage
            )
            Spacer(modifier = Modifier.padding(4.dp))
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SliderView(state: PagerState, viewModel: GameScreenshotsViewModel) {

    val imageUrl = remember { mutableStateOf("") }

    HorizontalPager(
        state = state,
        beyondBoundsPageCount = viewModel.screenShots.size,
        modifier = Modifier
            .aspectRatio(4/3F,false)
            .fillMaxWidth()
    ) { page ->

        imageUrl.value = viewModel.screenShots[page].image

        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(contentAlignment = Alignment.BottomCenter) {

                val painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = imageUrl.value)
                        .apply(block = fun ImageRequest.Builder.() {
                            scale(Scale.FILL)
                        }).build()
                )
                Image(
                    painter = painter, contentDescription = "",
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                        .padding(top = 16.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .fillMaxSize(),
                )
            }
        }
    }
}

@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int
) {

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(), horizontalArrangement = Arrangement.Center
    ) {

        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(color = MaterialTheme.colorScheme.onBackground)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(color = MaterialTheme.colorScheme.primaryContainer)
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}