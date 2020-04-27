package mx.test.cartelera.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mx.test.cartelera.ui.detail.DetailFragment
import mx.test.cartelera.ui.movie.MovieFragment

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMovieFragment(): MovieFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailFragment(): DetailFragment
}