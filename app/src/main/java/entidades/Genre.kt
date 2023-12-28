/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package entidades

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Genre(
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "image_background")
    val imageBackground: String? = null
)