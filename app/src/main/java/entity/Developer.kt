/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package entity

data class Developer(
    val id:Int,
    val name:String,
    val slug:String,
    val gameCount:Int,
    val imageBackgroundURL:String,
    val description:String
)