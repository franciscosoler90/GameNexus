/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package entidades

sealed class DetailScreenEvent {
    data class FavoriteGame(
        val id: Long,
        val favorite: Boolean
    ) : DetailScreenEvent()

    data class ShareGame(
        val gameId: Long? = null,
        val dismissed: Boolean = false
    ) : DetailScreenEvent()


}