package ru.mobile.permtravel.repositories

import android.content.Context
import kotlinx.coroutines.flow.Flow
import ru.mobile.permtravel.database.CDatabase
import ru.mobile.permtravel.model.Author
import ru.mobile.permtravel.model.Post
import java.util.UUID

class CRepositoryPosts (
    context : Context
)
{
    private val db = CDatabase.getDatabase(context)
    private val daoPosts = db.daoPosts()
    private val daoAuthors = db.daoAuthors()

    fun getPostsByAuthorId(authorId: UUID) : Flow<List<Post?>>
    {
        return daoPosts.getPostsByAuthorId(authorId)
    }

    fun getAuthorById(authorId: UUID) : Flow<Author?> {
        return daoAuthors.getById(authorId)
    }

    suspend fun insertPost(post: Post)
    {
        daoPosts.insertPost(post)
    }
}