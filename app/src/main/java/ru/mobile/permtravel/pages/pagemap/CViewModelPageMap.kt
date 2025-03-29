package ru.mobile.permtravel.pages.pagemap

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.mobile.permtravel.model.CPlace
import ru.mobile.permtravel.repositories.CRepositoryPlaces

class CViewModelPageMap(application: Application) : AndroidViewModel(application) {
    // Используем репозиторий с достопримечательностями
    private val repositoryPlaces = CRepositoryPlaces(application)
    val places: StateFlow<List<CPlace>> = repositoryPlaces.getAll()
        .stateIn(viewModelScope, SharingStarted.Lazily, listOf())
    init {
        // При запуске пытаетмся получить информацию с сервера
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repositoryPlaces.updatePlacesFromServer()
            } catch (e: Exception) {
                Log.e("ViewModel", "Не удалось обновить с сервера, загружаем локальные данные", e)
            }
        }
    }

}