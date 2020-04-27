package mx.test.cartelera.di

import android.app.Application
import mx.test.cartelera.CarteleraApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    MovieActivityModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(carteleraApp: CarteleraApp)
}