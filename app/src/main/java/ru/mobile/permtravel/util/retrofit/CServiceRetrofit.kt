package ru.mobile.permtravel.util.retrofit

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// Замените на ваш IP компа если запускаете на устройстве
// Если в эмуляторе используйте http://10.0.2.2:8080
// Если удалённый сервер, используйте его
private const val BASE_URL =
    "https://permtravel.yardtech.ru"

val moshi : Moshi = Moshi.Builder()
    .add(CAdapterUUID())
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object CAPIPlaces {
    val retrofitService : IAPIPlaces by lazy {
        retrofit.create(IAPIPlaces::class.java)
    }
}

object  CAPIAuthors {
    val retrofitService : IAPIAuthors by lazy {
        retrofit.create(IAPIAuthors::class.java)
    }
}

object  CAPIPosts {
    val retrofitService : IAPIPosts by lazy {
        retrofit.create(IAPIPosts::class.java)
    }
}