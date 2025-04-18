package ru.mobile.permtravel.repositories

import android.content.Context
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toSet
import ru.mobile.permtravel.database.CDatabase
import ru.mobile.permtravel.model.Author
import ru.mobile.permtravel.model.Post
import ru.mobile.permtravel.util.retrofit.CAPIAuthors
import ru.mobile.permtravel.util.retrofit.CAPIPosts
import java.util.UUID

class CRepositoryPosts (
    context : Context
)
{
    private val db = CDatabase.getDatabase(context)
    private val daoPosts = db.daoPosts()
    private val daoAuthors = db.daoAuthors()

//    fun getPostsByAuthorId(authorId: UUID) : Flow<List<Post?>>
//    {
//        return daoPosts.getPostsByAuthorId(authorId)
//    }
    fun getPostsByAuthorId(authorId: UUID) : Flow<List<Post?>>
    {
        return flow { emit(CAPIPosts.retrofitService.findAllById(authorId)) }
    }

//    fun getAuthorById(authorId: UUID) : Flow<Author?> {
//        return daoAuthors.getById(authorId)
//    }

    fun getAuthorById(authorId: UUID) : Flow<Author?> {
        return  flow { emit(CAPIAuthors.retrofitService.getAuthorById(authorId))}
    }

    suspend fun insertPost(post: Post)
    {
//        try {
//            daoPosts.insertPost(post)
//        } catch (e: Exception) {
//            Log.e("insertPost", "Error inserting post: ${e.message}")
//        }
        CAPIPosts.retrofitService.createPost(post)
    }
}