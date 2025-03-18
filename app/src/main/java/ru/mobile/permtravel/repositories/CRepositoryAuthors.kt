package ru.mobile.permtravel.repositories

import android.content.Context
import kotlinx.coroutines.flow.Flow
import ru.mobile.permtravel.database.CDatabase
import ru.mobile.permtravel.model.Author
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

    fun getAuthorById(id: UUID): Flow<Author?> {
        return daoAuthors.getById(id)
    }
}