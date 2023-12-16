/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package entidades

data class Developer(
    val id:Int,
    val name:String,
    val slug:String,
    val gameCount:Int,
    val imageBackgroundURL:String,
    val description:String
)