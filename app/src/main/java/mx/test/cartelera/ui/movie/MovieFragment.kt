package mx.test.cartelera.ui.movie

import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.manager.SupportRequestManagerFragment
import mx.test.cartelera.AppExecutors

import mx.test.cartelera.R
import mx.test.cartelera.binding.FragmentDataBindingComponent
import mx.test.cartelera.databinding.FragmentMovieBinding
import mx.test.cartelera.di.Injectable
import mx.test.cartelera.ui.common.MovieListAdapter
import mx.test.cartelera.ui.common.RetryCallback
import mx.test.cartelera.ui.dialog.DialogMovie
import mx.test.cartelera.utils.autoCleared
import java.lang.Exception
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val movieViewModel: MovieViewModel by viewModels {
        viewModelFactory
    }

    @Inject
    lateinit var appExecutors: AppExecutors

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    var binding by autoCleared<FragmentMovieBinding>()

    private var adapter by autoCleared<MovieListAdapter>()

    private var dialogListener: DialogListener? = null

    private fun initMovieList(viewModel: MovieViewModel) {
        viewModel.movies.observe(viewLifecycleOwner, Observer{
            listResource ->
            if(listResource?.data != null) {
                adapter.submitList(listResource.data)
            } else {
                adapter.submitList(emptyList())
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_movie, container, false)
        val dataBinding = DataBindingUtil.inflate<FragmentMovieBinding>(
            inflater,
            R.layout.fragment_movie,
            container,
            false
        )

        dataBinding.retryCallback = object : RetryCallback {
            override fun retry() {
                movieViewModel.retry()
            }
        }

        binding = dataBinding

        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(R.transition.move)

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //val params = MovieFragmentsArgs.fromBundle(arguments!!)
        //movieViewModel.setId(params.owner, params.name)
        binding.setLifecycleOwner(viewLifecycleOwner)
        //binding.movie = viewModel.movie

        val adapter = MovieListAdapter(dataBindingComponent, appExecutors,
            { movie ->
                findNavController().navigate(
                    MovieFragmentDirections.actionMovieFragmentToDetailFragment(
                        movie
                    )
                )
            }, { movie -> dialogListener?.onLoanchDialog(movie.posterPath, movie.rating) }
        )

        this.adapter = adapter
        binding.movieList.adapter = adapter

        initMovieList(movieViewModel)

    }

    override fun onAttach(context: Context) {
        dialogListener = context as DialogListener
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
        dialogListener = null
    }

    interface DialogListener {
        fun onLoanchDialog(url: String?, rating: String)
    }
}
