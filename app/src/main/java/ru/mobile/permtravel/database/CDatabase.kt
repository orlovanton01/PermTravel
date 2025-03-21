package ru.mobile.permtravel.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.mobile.permtravel.database.dao.CDAOAuthors
import ru.mobile.permtravel.database.dao.CDAOPlaces
import ru.mobile.permtravel.database.dao.CDAOPosts
import ru.mobile.permtravel.model.Author
import ru.mobile.permtravel.model.CPlace
import ru.mobile.permtravel.model.Post

@Database(entities = [CPlace::class, Author::class, Post::class], version = 14)
abstract class CDatabase : RoomDatabase() {
    abstract fun daoPlaces(): CDAOPlaces
    abstract fun daoAuthors(): CDAOAuthors
    abstract fun daoPosts(): CDAOPosts

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