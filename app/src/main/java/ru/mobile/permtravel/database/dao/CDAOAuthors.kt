package ru.mobile.permtravel.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.mobile.permtravel.model.Author
import java.util.UUID

@Dao
interface CDAOAuthors {
    @Query("SELECT * FROM authors")
    fun getAll(): Flow<List<Author>>

    @Query("SELECT * FROM authors WHERE id=:id")
    fun getById(id: UUID): Flow<Author?>

    @Insert(onConflict = REPLACE)
    fun insert(authors : Author)
}