package ru.mobile.permtravel.pages.pageplaces

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.mobile.permtravel.R
import ru.mobile.permtravel.model.CPlace
import ru.mobile.permtravel.repositories.CRepositoryPlaces
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.util.UUID

class CViewModelPagePlaces(application: Application) :  AndroidViewModel(application) {
    private val repositoryPlaces = CRepositoryPlaces(application)
    val places: StateFlow<List<CPlace>> = repositoryPlaces.getAll()
        .stateIn(viewModelScope, SharingStarted.Lazily, listOf(
            CPlace(UUID.randomUUID(), "Загрузка...", "", "Ожидаем данные...",0.0, 0.0)
        ))
    //Это кусок кода для вставки тестовых элементов в БД.
    init {
        val context = getApplication<Application>().applicationContext
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repositoryPlaces.updatePlacesFromServer()
            } catch (e: Exception) {
                Log.e("ViewModel", "Не удалось обновить с сервера, загружаем локальные данные", e)
            }

            val placesList = repositoryPlaces.getAll().first()
            for (place in placesList) {
                if (place.photoPath.isEmpty() || !File(place.photoPath).exists()) {
                    val localPath = try {
                        downloadImage(
                            getApplication(),
                            "http://192.168.0.193:8080/files/${place.id}",
                            place.id.toString()
                        )
                    } catch (e: Exception) {
                        Log.e("ViewModel", "Ошибка загрузки изображения", e)
                        null
                    }
                    if (localPath != null) {
                        place.photoPath = localPath
                        repositoryPlaces.update(place) // Обновляем путь в БД
                    }
                }
            }
        }
    }


    private fun downloadImage(context: Context, imageUrl: String, placeId: String): String? {
        val client = OkHttpClient()
        val request = Request.Builder().url(imageUrl).build()

        return try {
            val response = client.newCall(request).execute()
            if (!response.isSuccessful) return null

            // Получаем MIME-тип (image/jpeg, image/png и т. д.)
            val contentType = response.header("Content-Type", "") ?: ""
            val extension = when {
                contentType.contains("jpeg") -> "jpg"
                contentType.contains("png") -> "png"
                else -> "webp"  // По умолчанию
            }

            val imagesDir = File(context.filesDir, "images")
            if (!imagesDir.exists()) imagesDir.mkdirs()

            val imageFile = File(imagesDir, "$placeId.$extension")
            val inputStream: InputStream? = response.body?.byteStream()
            val outputStream = FileOutputStream(imageFile)

            inputStream?.copyTo(outputStream)
            inputStream?.close()
            outputStream.close()

            imageFile.absolutePath
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }



}