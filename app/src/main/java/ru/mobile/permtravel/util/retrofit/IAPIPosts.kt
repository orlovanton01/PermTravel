package ru.mobile.permtravel.util.retrofit

import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID
import ru.mobile.permtravel.model.Post

interface IAPIPosts {
    @GET("/posts/{authorId}")
    suspend fun findAllById(@Path("authorId") authorId: UUID): List<Post?>

    @POST("/posts")
    suspend fun  createPost(@Body post: Post): Post

    data class CreatePostRequest(
        val authorId: UUID,
        val text: String?,
        val placeId: UUID? = null
    )
}