package ru.mobile.permtravel.util.retrofit

import retrofit2.http.GET
import ru.mobile.permtravel.model.CPlace

interface IAPIPlaces {
    @GET("places")
    suspend fun getPlaces() : List<CPlace>
}