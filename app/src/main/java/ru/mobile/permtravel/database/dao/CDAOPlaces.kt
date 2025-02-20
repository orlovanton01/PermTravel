package ru.mobile.permtravel.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.mobile.permtravel.model.CPlace
import java.util.UUID

@Dao
interface CDAOPlaces {
    @Query("SELECT * FROM places")
    fun getAll(): Flow<List<CPlace>>

    @Query("SELECT * FROM places WHERE id=:id")
    fun getById(id: UUID): Flow<CPlace?>

    @Insert(onConflict = REPLACE)
    fun insert(checkPoint : CPlace)
}