/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@Entity(tableName = "favorite_games")
data class GameEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "background_image") val background_image: String?,
    @ColumnInfo(name = "released") val released: String?,
    @ColumnInfo(name = "rating") val rating: Float,
    @ColumnInfo(name = "genres") val genres: List<Genre>,
    @ColumnInfo(name = "userId") val userId: String
)

@JsonClass(generateAdapter = true)
data class Game(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "slug") val slug: String,
    @Json(name = "background_image") val background_image: String?,
    @Json(name = "description") val description: String,
    @Json(name = "description_raw") val description_raw: String,
    @Json(name = "released") val released: String?,
    @Json(name = "metacritic") val metacritic: Int,
    @Json(name = "rating") val rating: Float,
    @Json(name = "platforms") val platforms: List<Platforms>,
    @Json(name = "genres") val genres: List<Genre>,
    @Json(name = "developers") val developers: List<Developer>,
    @Json(name = "publishers") val publishers: List<Publisher>,
)


class GenreListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromGenreList(genres: List<Genre>): String {
        return gson.toJson(genres)
    }

    @TypeConverter
    fun toGenreList(genreString: String): List<Genre> {
        val type = object : TypeToken<List<Genre>>() {}.type
        return gson.fromJson(genreString, type)
    }
}