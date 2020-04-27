package mx.test.cartelera.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mx.test.cartelera.MovieActivity

@Module
abstract class MovieActivityModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMovieActivity(): MovieActivity
}