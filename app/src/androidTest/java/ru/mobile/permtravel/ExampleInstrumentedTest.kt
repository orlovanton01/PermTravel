package ru.mobile.permtravel

import android.content.Context
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import ru.mobile.permtravel.repositories.CRepositoryPlaces

@RunWith(AndroidJUnit4::class)
class PlacesRealDatabaseTest {

    @Test
    fun testPlacesCountAfterServerUpdate() = runBlocking {
        val context = ApplicationProvider.getApplicationContext<Context>()

        // Получаем репозиторий
        val repositoryPlaces = CRepositoryPlaces(context)

        // 1. Ждём загрузку данных с сервера
        try {
            repositoryPlaces.updatePlacesFromServer()
        } catch (e: Exception) {
            Log.e("InstrumentedTest", "Не удалось обновить с сервера, " +
                    "загружаем локальные данные", e)
        }

        // 2. Только теперь читаем и проверяем
        val placesList = repositoryPlaces.getAll().first()

        // 3. Проверка: их должно быть 10
        assertEquals(10, placesList.size)
    }
}
