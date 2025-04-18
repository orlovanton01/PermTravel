package ru.mobile.permtravel.pages.pageplacedescription

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import ru.mobile.permtravel.model.CPlace
import ru.mobile.permtravel.repositories.CRepositoryPlaces
import java.util.UUID

class CViewModelPagePlaceDescription(application: Application) :  AndroidViewModel(application) {
    private val repositoryPlaces = CRepositoryPlaces(application)

    fun getPlaceById(id: UUID): StateFlow<CPlace?> {
        return repositoryPlaces.getPlaceById(id)
            .stateIn(viewModelScope, SharingStarted.Lazily, null)
    }

    fun getPlaceByIdForPosts(id: UUID?): StateFlow<CPlace?> {
        return repositoryPlaces.getPlaceByIdForPosts(id)
            .stateIn(viewModelScope, SharingStarted.Lazily, null)
    }
}