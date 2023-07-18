/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package entity

data class Game(
    val id: Int,
    val metacritic: Int,
    val name: String,
    val slug: String,
    val name_original: String,
    val platforms: List<Platforms>,
    val genres: List<Genre>,
    val background_image: String?,
    val background_image_additional: String?,
    val description: String?,
    val description_raw: String?,
    val released: String?
)


