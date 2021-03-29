package com.github.ahmadriza.movie.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
data class MovieItem(
    @PrimaryKey val id: Long,
    @SerializedName("poster_path") val posterPath: String?,
    val adult: Boolean,
    val overview: String?,
    @SerializedName("release_date") val releaseDate: String,
    val title: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("vote_average") val voteAvg: Float,
    val isFavorite: Boolean = false

)

data class MovieGenre(val name: String)
data class MovieCompany(val name: String, @SerializedName("logo_path") val logo: String)
data class MovieCountry(val name: String)
data class MovieLanguage(val name: String)


data class MovieDetail(
    val id: Long,
    val title: String,
    @SerializedName("original_title") val originalTitle: String,
    val adult: Boolean,
    val overview: String?,
    val genres: List<MovieGenre>,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val voteAvg: Float,
    @SerializedName("production_companies") val productions: List<MovieCompany>,
    @SerializedName("production_countries") val countries: List<MovieCountry>,
    @SerializedName("spoken_languages") val languages: List<MovieLanguage>
)