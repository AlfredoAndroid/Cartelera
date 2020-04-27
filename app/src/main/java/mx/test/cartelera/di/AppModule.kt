package mx.test.cartelera.di

import android.app.Application
import androidx.room.Room
import mx.test.cartelera.api.ServiceApi
import mx.test.cartelera.db.CarteleraDb
import mx.test.cartelera.utils.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import mx.test.cartelera.db.MovieDao
import mx.test.cartelera.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideGithubApi(): ServiceApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(ServiceApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): CarteleraDb{
        return Room.databaseBuilder(app, CarteleraDb::class.java, "github.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(db: CarteleraDb): MovieDao {
        return db.movieDao()
    }
}