package mx.test.cartelera.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import mx.test.cartelera.AppExecutors
import mx.test.cartelera.R
import mx.test.cartelera.databinding.MovieItemBinding
import mx.test.cartelera.model.Movie

class MovieListAdapter (
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors,
    private val imageClickCallAdapter: ((Movie)->Unit)?,
    private val titleClickCallAdapter: ((Movie)->Unit)?
): DataBoundListAdapter<Movie, MovieItemBinding>(
   appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.overview == newItem.overview && oldItem.popularity == newItem.popularity
        }
    }
) {
    override fun createBinding(parent: ViewGroup): MovieItemBinding {
        val binding = DataBindingUtil.inflate<MovieItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.movie_item,
            parent,
            false,
            dataBindingComponent
        )
        binding.image.setOnClickListener{
            binding.movie?.let {
                imageClickCallAdapter?.invoke(it)
            }
        }

        binding.layoutTitulo.setOnClickListener{
            binding.movie?.let {
                titleClickCallAdapter?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: MovieItemBinding, item: Movie) {
        binding.movie = item
    }
}