/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package entidades

sealed class DetailScreenEvent {
    data class BookmarkGame(
        val id: Long,
        val bookmarked: Boolean
    ) : DetailScreenEvent()

    data class ShareGame(
        val gameId: Long? = null,
        val dismissed: Boolean = false
    ) : DetailScreenEvent()


}