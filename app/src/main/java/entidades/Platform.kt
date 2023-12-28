/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "platform_parent")
data class ParentPlatform(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name:String,
    @ColumnInfo(name = "slug") val slug:String,
    @ColumnInfo(name = "platforms") val platforms:List<Platform>
){
    @JsonClass(generateAdapter = true)
    @Entity(tableName = "platform")
    data class Platform(
        @PrimaryKey val id: Int,
        @ColumnInfo(name = "name") val name:String,
        @ColumnInfo(name = "slug") val slug:String,
        @ColumnInfo(name = "games_count") val games_count:Int,
        @ColumnInfo(name = "image_background") val image_background:String,
        @ColumnInfo(name = "description") val description:String
    )

}