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

@Entity(tableName = "game")
data class Game(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "slug") val slug: String,
    @ColumnInfo(name = "name_original") val name_original: String,
    @ColumnInfo(name = "background_image") val background_image: String?,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "description_raw") val description_raw: String,
    @ColumnInfo(name = "released") val released: String?,
    @ColumnInfo(name = "metacritic") val metacritic: Int,
    @ColumnInfo(name = "rating") val rating: Float,
    @ColumnInfo(name = "platforms") val platforms: List<ParentPlatform.Platform>,
    @ColumnInfo(name = "genres") val genres: List<Genre>,
    @ColumnInfo(name = "developers") val developers: List<Developer>,
    @ColumnInfo(name = "publishers") val publishers: List<Publisher>,
)

// Crear un convertidor para la lista de plataformas
class PlatformTypeConverter {
    @TypeConverter
    fun fromList(platforms: List<ParentPlatform.Platform>): String {
        // Convertir la lista a String para almacenarla en la base de datos
        return Gson().toJson(platforms)
    }

    @TypeConverter
    fun toList(platformsString: String): List<ParentPlatform.Platform> {
        // Convertir el String de la base de datos a una lista
        val listType = object : TypeToken<List<ParentPlatform.Platform>>() {}.type
        return Gson().fromJson(platformsString, listType)
    }
}

// Convertidor para la lista de géneros
class GenreTypeConverter {
    @TypeConverter
    fun fromList(genres: List<Genre>): String {
        return Gson().toJson(genres)
    }

    @TypeConverter
    fun toList(genresString: String): List<Genre> {
        val listType = object : TypeToken<List<Genre>>() {}.type
        return Gson().fromJson(genresString, listType)
    }
}

// Convertidor para la lista de desarrolladores
class DeveloperTypeConverter {
    @TypeConverter
    fun fromList(developers: List<Developer>): String {
        return Gson().toJson(developers)
    }

    @TypeConverter
    fun toList(developersString: String): List<Developer> {
        val listType = object : TypeToken<List<Developer>>() {}.type
        return Gson().fromJson(developersString, listType)
    }
}

// Convertidor para la lista de publishers
class PublisherTypeConverter {
    @TypeConverter
    fun fromList(publishers: List<Publisher>): String {
        return Gson().toJson(publishers)
    }

    @TypeConverter
    fun toList(publishersString: String): List<Publisher> {
        val listType = object : TypeToken<List<Publisher>>() {}.type
        return Gson().fromJson(publishersString, listType)
    }
}