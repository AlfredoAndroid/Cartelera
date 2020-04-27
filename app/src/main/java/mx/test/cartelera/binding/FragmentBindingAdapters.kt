package mx.test.cartelera.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import mx.test.cartelera.utils.BASE_IMAGE_URL
import javax.inject.Inject

class FragmentBindingAdapters @Inject constructor(val fragment: Fragment) {
    @BindingAdapter("imageUrl")
    fun bindImage(imageView: ImageView, url: String?){
        Glide.with(fragment).load(BASE_IMAGE_URL.plus(url)).into(imageView)
    }
}