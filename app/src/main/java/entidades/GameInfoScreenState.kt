/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package entidades

data class GameInfoScreenState(
    val game: Game? = null,
    val trailerUrl: String? = null,
    val isLoading: Boolean = false,
    val shareSheetGame: Game? = null,
    val error: String? = null,
    val listScreenshots : List<String> = emptyList(),
    var isFavoriteGame: Boolean = false
)
