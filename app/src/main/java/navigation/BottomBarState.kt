/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.fransoler.R
import common.Constant

enum class BottomBarState {
    HOME,
    SEARCH,
    FAVORITE
}

enum class BottomBarDestination(
    val direction: String,
    @StringRes val label: Int,
    val icon: ImageVector
) {
    Home(Constant.homeRoute, R.string.home, Icons.Rounded.Home),
    Search(Constant.searchRoute, R.string.search, Icons.Rounded.Search),
    Favorite(Constant.favoriteRoute, R.string.favorites, Icons.Rounded.Favorite),
}