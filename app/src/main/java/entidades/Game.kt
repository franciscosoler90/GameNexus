/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package entidades

data class Game(
    val id: Long,
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
    val released: String?,
    val developers: List<Developer>,
    val publishers: List<Publisher>,
    val isFavorite: Boolean,
    val rating: String?
)