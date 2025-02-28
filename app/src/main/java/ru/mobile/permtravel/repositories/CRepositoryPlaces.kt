package ru.mobile.permtravel.repositories

import android.content.Context
import kotlinx.coroutines.flow.Flow
import ru.mobile.permtravel.database.CDatabase
import ru.mobile.permtravel.model.CPlace
import java.util.UUID

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

    fun insert(
        place: CPlace
    )
    {
        daoPlaces.insert(place)
    }

    fun getPlaceById(id: UUID): Flow<CPlace?> {
        return daoPlaces.getById(id)
    }

}