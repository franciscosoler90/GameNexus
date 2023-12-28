/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package navigation

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDirections
import com.example.fransoler.R

enum class BottomBarState {
    HOME,
    SEARCH,
    FAVORITE
}


enum class BottomBarDestination(
    val direction: NavDirections,
    @StringRes val label: Int,
    val icon: ImageVector
) {
    Home(HomeScreenDestination, R.string.home, Icons.Rounded.Home),
    Search(SearchScreenDestination, R.string.search, Icons.Rounded.Search),
    Favorite(FavoriteScreenDestination, R.string.favorites, Icons.Rounded.Favorite),
}


object HomeScreenDestination : NavDirections {
    override val actionId: Int
        get() = TODO("Not yet implemented")
    override val arguments: Bundle
        get() = TODO("Not yet implemented")

}

object SearchScreenDestination : NavDirections {
    override val actionId: Int
        get() = TODO("Not yet implemented")
    override val arguments: Bundle
        get() = TODO("Not yet implemented")

}

object FavoriteScreenDestination : NavDirections {
    override val actionId: Int
        get() = TODO("Not yet implemented")
    override val arguments: Bundle
        get() = TODO("Not yet implemented")

}