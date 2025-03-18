package ru.mobile.permtravel.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.mobile.permtravel.model.Author
import ru.mobile.permtravel.model.Post
import java.util.UUID


@Dao
interface CDAOPosts {
    @Query("SELECT * FROM posts WHERE authorId =:authorId ORDER BY createdAt DESC")
    fun getPostsByAuthorId(authorId: UUID): Flow<List<Post?>>

    @Insert(onConflict = REPLACE)
    suspend fun insertPost(posts : Post)
}
