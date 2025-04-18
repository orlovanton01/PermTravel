package ru.mobile.permtravel.repositories

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import ru.mobile.permtravel.database.CDatabase
import ru.mobile.permtravel.model.CPlace
import ru.mobile.permtravel.util.retrofit.CAPIPlaces
import java.util.UUID

class CRepositoryPlaces(
    context : Context
)
{
    private val db = CDatabase.getDatabase(context)
    private val daoPlaces = db.daoPlaces()

    fun getAll() : Flow<List<CPlace>>
    {
        return daoPlaces.getAll()
    }

    fun getPlaceById(id: UUID): Flow<CPlace?> {
        return daoPlaces.getById(id)
    }

    fun getPlaceByIdForPosts(id: UUID?): Flow<CPlace?> {
        return daoPlaces.getByIdForPosts2(id)
    }

    private fun deleteById(id: UUID) {
        return daoPlaces.deleteById(id)
    }

    private fun insertAll(
        places: List<CPlace>
    )
    {
        daoPlaces.insertAll(places)
    }

    suspend fun updatePlacesFromServer() {
        val serverPlaces = CAPIPlaces.retrofitService.getPlaces() // Получаем данные с сервера
        val localPlaces = daoPlaces.getAll().first() // Получаем данные из локальной БД

        val serverPlaceIds = serverPlaces.map { it.id }.toSet()
        val localPlaceIds = localPlaces.map { it.id }.toSet()

        // Удаляем из БД записи, которых нет на сервере
        val toDelete = localPlaceIds - serverPlaceIds
        toDelete.forEach { deleteById(it) }

        // Добавляем или обновляем записи из сервера
        val toInsertOrUpdate = serverPlaces.filter { it.id !in localPlaceIds }
        insertAll(toInsertOrUpdate)
    }

    fun update(
        place: CPlace
    )
    {
        daoPlaces.update(place)
    }
}