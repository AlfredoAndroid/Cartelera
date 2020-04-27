package mx.test.cartelera.ui.detail

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide

import mx.test.cartelera.R
import mx.test.cartelera.binding.FragmentDataBindingComponent
import mx.test.cartelera.databinding.FragmentDetailBinding
import mx.test.cartelera.di.Injectable
import mx.test.cartelera.utils.BASE_IMAGE_URL
import mx.test.cartelera.utils.autoCleared
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val detailViewModel: DetailViewModel by viewModels {
        viewModelFactory
    }
    var binding by autoCleared<FragmentDetailBinding>()

    private val params by navArgs<DetailFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_detail, container, false)

        val dataBinding = DataBindingUtil.inflate<FragmentDetailBinding>(
            inflater,
            R.layout.fragment_detail,
            container,
            false
        )

        binding = dataBinding

        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(R.transition.move)

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val params = DetailFragmentArgs.fromBundle(arguments!!)

        binding.apply {
            setLifecycleOwner(viewLifecycleOwner)
            movie = params.movie
            Glide.with(this@DetailFragment).load(BASE_IMAGE_URL.plus(params.movie.posterPath)).into(imageDetail)
            viewModel = detailViewModel

            switchOverview.setOnCheckedChangeListener { buttonView, isChecked -> detailViewModel.setVisibleOverview(isChecked) }

        }


    }
}
