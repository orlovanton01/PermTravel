package ru.mobile.permtravel.repositories

import android.content.Context
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.toSet
import ru.mobile.permtravel.database.CDatabase
import ru.mobile.permtravel.model.Author
import ru.mobile.permtravel.util.retrofit.CAPIAuthors
import java.util.UUID

class CRepositoryAuthors (
    context : Context
)
{
    private val db = CDatabase.getDatabase(context)
    private val daoAuthors = db.daoAuthors()

    fun getAll() : Flow<List<Author>>
    {
        return daoAuthors.getAll()
    }

    fun insert(
        author: Author
    )
    {
        daoAuthors.insert(author)
    }

    private fun deleteById(id: UUID) {
        return daoAuthors.deleteById(id)
    }

    private fun insertAll(
        authors: List<Author>
    )
    {
        daoAuthors.insertAll(authors)
    }

    fun update(
        author: Author
    )
    {
        daoAuthors.insert(author)
    }

    suspend fun updateAuthorsFromServer() {
        val serverAuthors = CAPIAuthors.retrofitService.getAllAuthors() //данные с сервера
        val localAuthor = daoAuthors.getAll().first() // Данные из локальной бд

        val serverAuthorIds = serverAuthors.map { it.id }.toSet()
        val localAuthorIds = localAuthor.map {it.id}.toSet()

        //Удаляем из БД записи, которых нет на сервере
        val toDelete = localAuthorIds - serverAuthorIds
        toDelete.forEach {
            Log.i("deleteById","${it}")
            deleteById(it)
        }

        //Добавление или обновление записи из сервера
        val toInsertOrUpdate = serverAuthors.filter { it.id !in localAuthorIds }
        insertAll(toInsertOrUpdate)
    }

    fun getAuthorById(id: UUID): Flow<Author?> {
        return daoAuthors.getById(id)
    }
}