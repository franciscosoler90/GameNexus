/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package entidades

sealed class GameInfoScreenEvent {
    data class FavoriteGame(
        val id: Long,
        val favorite: Boolean
    ) : GameInfoScreenEvent()

    data class ShareGame(
        val gameId: Long? = null,
        val dismissed: Boolean = false
    ) : GameInfoScreenEvent()


}