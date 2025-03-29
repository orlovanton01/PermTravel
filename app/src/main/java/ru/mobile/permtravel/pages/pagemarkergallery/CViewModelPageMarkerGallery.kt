package ru.mobile.permtravel.pages.pagemarkergallery

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import ru.mobile.permtravel.model.CPlace
import ru.mobile.permtravel.repositories.CRepositoryPlaces
import java.util.UUID


class CViewModelPageMarkerGallery(application: Application) :  AndroidViewModel(application) {
    // Используем репозиторий мест
    private val repositoryPlaces = CRepositoryPlaces(application)

    // Получение места по идентификатору
    fun getPlaceById(id: UUID): StateFlow<CPlace?> {
        return repositoryPlaces.getPlaceById(id)
            .stateIn(viewModelScope, SharingStarted.Lazily, null)
    }
}
