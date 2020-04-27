package mx.test.cartelera.repository

import androidx.lifecycle.LiveData
import mx.test.cartelera.AppExecutors
import mx.test.cartelera.api.ApiResponse
import mx.test.cartelera.api.ServiceApi
import mx.test.cartelera.db.CarteleraDb
import mx.test.cartelera.db.MovieDao
import mx.test.cartelera.model.Movie
import mx.test.cartelera.model.Movies
import mx.test.cartelera.utils.API_KEY
import mx.test.cartelera.utils.LANGUAGE
import mx.test.cartelera.utils.RateLimiter
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val db: CarteleraDb,
    private val movieDao: MovieDao,
    private val serviceApi: ServiceApi
){
    private val repoListRateLimiter = RateLimiter<Integer>(10, TimeUnit.MINUTES)

    fun loadMovies(): LiveData<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, Movies>(appExecutors) {
            override fun saveCallResult(item: Movies) {
                movieDao.insertMovies(item.movies)
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<Movie>> {
                return movieDao.loadMovies()
            }

            override fun createCall(): LiveData<ApiResponse<Movies>> {
                return serviceApi.getMovies(API_KEY, LANGUAGE)
            }

        }.asLiveData()
    }

    fun loadMovie(movieId: Integer) : LiveData<Resource<Movie>> {
        return object : NetworkBoundResource<Movie, Movie>(appExecutors) {
            override fun saveCallResult(item: Movie) {
                movieDao.insert(item)
            }

            override fun shouldFetch(data: Movie?): Boolean {
                return data == null || repoListRateLimiter.shoulFetch(movieId)
            }

            override fun loadFromDb(): LiveData<Movie> {
                return movieDao.load(movieId)
            }

            override fun createCall(): LiveData<ApiResponse<Movie>> {
                return serviceApi.getMovie(movieId, API_KEY, LANGUAGE)
            }

            override fun onFetchFailed() {
                repoListRateLimiter.reset(movieId)
            }

        }.asLiveData()
    }

}