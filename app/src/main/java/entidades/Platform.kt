/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package entidades

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Platform(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name:String,
    @Json(name = "slug") val slug:String,
    @Json(name = "games_count") val games_count:Int,
    @Json(name = "image_background") val image_background:String,
    @Json(name = "description") val description:String
)

@JsonClass(generateAdapter = true)
data class Platforms(
    @Json(name = "platform") val platform:Platform
)

@JsonClass(generateAdapter = true)
data class PlatformParent(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name:String,
    @Json(name = "slug") val slug:String,
    @Json(name = "platforms") val platforms:List<Platform>
)