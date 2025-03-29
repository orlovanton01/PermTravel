package ru.mobile.permtravel.pages.pagemap

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
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
import ru.mobile.permtravel.model.Author
import ru.mobile.permtravel.model.CPlace
import ru.mobile.permtravel.repositories.CRepositoryAuthors
import ru.mobile.permtravel.repositories.CRepositoryPlaces
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.util.UUID

class CViewModelPageMap(application: Application) : AndroidViewModel(application) {

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
        }
    }

}