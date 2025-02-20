package ru.mobile.permtravel.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.mobile.permtravel.database.dao.CDAOPlaces
import ru.mobile.permtravel.model.CPlace

@Database(entities = [CPlace::class], version = 1)
abstract class CDatabase : RoomDatabase() {
    abstract fun daoPlaces(): CDAOPlaces

    companion object {
        @Volatile
        private var INSTANCE: CDatabase? = null
        fun getDatabase(context: Context): CDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(
                            context,
                            CDatabase::class.java,
                            "permtravel.db"
                        )
                            .build()
                }

            }
            return INSTANCE!!
        }
    }
}