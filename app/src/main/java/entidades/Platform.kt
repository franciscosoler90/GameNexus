/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package entidades

data class Platform(
    val id: Int,
    val name:String,
    val slug:String,
    val games_count:Int,
    val image_background:String,
    val description:String
    )

data class Platforms(
    val platform:Platform
    )


data class PlatformParent(
    val id: Int,
    val name:String,
    val slug:String,
    val platforms:List<Platform>
)