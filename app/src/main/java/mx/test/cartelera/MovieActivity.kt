package mx.test.cartelera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import mx.test.cartelera.ui.dialog.DialogMovie
import mx.test.cartelera.ui.movie.MovieFragment
import javax.inject.Inject

class MovieActivity : AppCompatActivity(),
    HasSupportFragmentInjector,
        MovieFragment.DialogListener
{

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onLoanchDialog(url: String?, rating: String) {
        val dialogFragment = DialogMovie()
        val bundle = Bundle()
        bundle.putString("URL", url)
        bundle.putString("RATING", rating)
        dialogFragment.arguments = bundle

        val ft = supportFragmentManager.beginTransaction()
        val prev = supportFragmentManager.findFragmentByTag("dialog")
        if (prev != null) {
            ft.remove(prev)
        }
        ft.addToBackStack(null)
        dialogFragment.show(ft, "dialog")
    }
}
