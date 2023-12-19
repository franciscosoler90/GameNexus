/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game")
data class Game(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "metacritic") val metacritic: Int,
    @ColumnInfo(name = "slug") val slug: String,
    @ColumnInfo(name = "name_original") val name_original: String,
    @ColumnInfo(name = "background_image") val background_image: String?,
    @ColumnInfo(name = "background_image_additional") val background_image_additional: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "description_raw") val description_raw: String?,
    @ColumnInfo(name = "released") val released: String?,
    @ColumnInfo(name = "rating") val rating: String?,
    @ColumnInfo(name = "platforms") val platforms: List<Platforms>,
    @ColumnInfo(name = "genres") val genres: List<Genre>,
    @ColumnInfo(name = "developers") val developers: List<Developer>,
    @ColumnInfo(name = "publishers") val publishers: List<Publisher>,
    @ColumnInfo(name = "isFavorite") val isFavorite: Boolean,
)