/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package entidades

data class ScreenShot(
    val id: Int,
    val image: String,
    val is_deleted: Boolean,
    val width: Int,
    val height: Int
)