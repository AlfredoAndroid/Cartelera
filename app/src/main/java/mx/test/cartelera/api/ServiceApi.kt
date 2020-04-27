package mx.test.cartelera.api

import androidx.lifecycle.LiveData
import io.reactivex.Observable
import mx.test.cartelera.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {
    @GET("movie/popular")
    fun getMovies(
        @Query("api_key") api: String,
        @Query("language") lenguaje: String
    ): LiveData<ApiResponse<Movies>>

    @GET("movie/{movie_id}")
    fun getMovie(
        @Path("movie_id") movieId: Integer,
        @Query("api_key") api: String,
        @Query("language") lenguaje: String
    ): LiveData<ApiResponse<Movie>>

}