package ru.mobile.permtravel

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import ru.mobile.permtravel.database.CDatabase
import ru.mobile.permtravel.repositories.CRepositoryPlaces

@RunWith(AndroidJUnit4::class)
class PlacesRealDatabaseTest {

    @Test
    fun testPlacesCountAfterServerUpdate() = runBlocking {
        val context = ApplicationProvider.getApplicationContext<Context>()

        // Получаем реальную БД и репозиторий
        val db = CDatabase.getDatabase(context)
        val repository = CRepositoryPlaces(context)

        // 1. Ждём загрузку данных с сервера
        repository.updatePlacesFromServer()

        // 2. Только теперь читаем и проверяем
        val places = db.daoPlaces().getAll().first()

        // 3. Проверка: их должно быть 10
        assertEquals(10, places.size)
    }
}
