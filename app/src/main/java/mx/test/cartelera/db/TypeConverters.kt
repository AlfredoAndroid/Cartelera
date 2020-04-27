package mx.test.cartelera.db

import android.util.Log
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import mx.test.cartelera.model.Movie
import java.lang.NumberFormatException

object GithubTypeConverters {
    @TypeConverter
    @JvmStatic
    fun stringToIntList(data: String?): List<Int>?{
        return data?.let{
            it.split(",").map{
                try{
                    it.toInt()
                } catch (ex: NumberFormatException){
                    Log.d("TAG1", "No puede convertir a numero")
                    null
                }
            }?.filterNotNull()
        }
    }

    @TypeConverter
    @JvmStatic
    fun intListToString(ints: List<Int>?): String? {
        return ints?.joinToString ( "," )
    }
}

object DataMovieConverter {

    @TypeConverter
    @JvmStatic
    fun fromMovieList(value: List<Movie>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Movie>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    @JvmStatic
    fun toMovieList(value: String): List<Movie> {
        val gson = Gson()
        val type = object : TypeToken<List<Movie>>() {}.type
        return gson.fromJson(value, type)
    }
}