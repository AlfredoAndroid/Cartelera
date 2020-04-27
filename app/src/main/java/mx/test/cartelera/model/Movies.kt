package mx.test.cartelera.model

import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("results") val movies: List<Movie>
)