package mx.test.cartelera.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import mx.test.cartelera.model.Movie
import mx.test.cartelera.repository.MovieRepository
import mx.test.cartelera.repository.Resource
import mx.test.cartelera.utils.AbsentLiveData
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    movieRepository: MovieRepository
): ViewModel() {

    private val _id = MutableLiveData<Integer>()
    val id: LiveData<Integer>
        get() = _id

    val movies: LiveData<Resource<List<Movie>>> = movieRepository.loadMovies()

    fun setId(id: Integer?) {
        if(_id.value != id) {
            _id.value = id
        }
    }

    fun retry() {
        _id.value?.let {
            _id.value = it
        }
    }
}