package mx.test.cartelera.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mx.test.cartelera.model.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovies(movies: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun createMovieIfNotExits(movie: Movie): Long

    @Query("SELECT * FROM movie WHERE id = :movieId")
    abstract fun load(movieId: Integer): LiveData<Movie>

    @Query("SELECT * FROM movie")
    abstract  fun loadMovies(): LiveData<List<Movie>>
}