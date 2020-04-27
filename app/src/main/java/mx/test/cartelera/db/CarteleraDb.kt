package mx.test.cartelera.db

import androidx.room.Database
import androidx.room.RoomDatabase
import mx.test.cartelera.model.*

@Database(
    entities = [Movie::class],
    version = 3
)
abstract class CarteleraDb: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}