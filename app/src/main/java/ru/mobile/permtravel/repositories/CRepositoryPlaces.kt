package ru.mobile.permtravel.repositories

import android.content.Context
import kotlinx.coroutines.flow.Flow
import ru.mobile.permtravel.database.CDatabase
import ru.mobile.permtravel.model.CPlace

class CRepositoryPlaces(
    context : Context
)
{
    private val db = CDatabase.getDatabase(context)
    private val daoPlaces = db.daoPlaces()

    fun getAll() : Flow<List<CPlace>>
    {
        //Обмен с сервером

        return daoPlaces.getAll()
    }

    suspend fun insert(
        checkPoint: CPlace
    )
    {
        daoPlaces.insert(checkPoint)
    }
}