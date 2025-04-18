package ru.mobile.permtravel.util.retrofit

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import ru.mobile.permtravel.model.Author
import java.util.UUID

interface IAPIAuthors {
    @GET("/authors")
    suspend fun getAllAuthors() : List<Author>

    @GET("/authors/{id}")
    suspend fun getAuthorById(@Path("id") id: UUID): Author?
}