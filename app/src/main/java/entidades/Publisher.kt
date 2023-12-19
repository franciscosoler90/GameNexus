/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "publisher")
data class Publisher(
    @PrimaryKey val id:Int,
    @ColumnInfo(name = "name") val name:String,
    @ColumnInfo(name = "slug") val slug:String,
    @ColumnInfo(name = "image_background") val image_background:String,
)