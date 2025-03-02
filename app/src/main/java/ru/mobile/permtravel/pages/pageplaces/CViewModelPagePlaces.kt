package ru.mobile.permtravel.pages.pageplaces

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.mobile.permtravel.R
import ru.mobile.permtravel.model.CPlace
import ru.mobile.permtravel.repositories.CRepositoryPlaces
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.UUID

class CViewModelPagePlaces(application: Application) :  AndroidViewModel(application) {
    private val repositoryPlaces = CRepositoryPlaces(application)
    val places: StateFlow<List<CPlace>> = repositoryPlaces.getAll()
        .stateIn(viewModelScope, SharingStarted.Lazily, listOf(
            CPlace(UUID.randomUUID(), "Загрузка...", "", "Ожидаем данные...")
        ))
    //Это кусок кода для вставки тестовых элементов в БД.
    init {
        val context = getApplication<Application>().applicationContext
        viewModelScope.launch(Dispatchers.IO) {
//            if (repositoryPlaces.getAll().first().isEmpty()) {
                insertTestData(context)
//            }
        }
    }

    private fun insertTestData(context: Context) {
        repositoryPlaces.insert(
            CPlace(
                UUID.randomUUID(),
                "Усьвинские столбы",
                saveMipmapToFile(context, R.mipmap.usvinsky_pillars, "usvinsky_pillars")?:"",
                "Усьвинские столбы — это впечатляющий природный памятник, расположенный на берегу реки Усьва в Пермском крае. Эти величественные каменные образования тянутся вдоль берега на несколько километров и достигают высоты до 150 метров . Столбы представляют собой скальные выходы известняка, которые начали формироваться еще в Пермский период, около 200-300 миллионов лет назад .\n" +
                        "\n" +
                        "Усьвинские столбы являются популярным туристическим объектом, привлекающим тысячи посетителей каждый год. С вершины столбов открываются захватывающие виды на изгибы реки Усьва и окрестные ландшафты. На верхней части тропы расположены несколько смотровых площадок, откуда можно насладиться панорамными видами .\n" +
                        "\n" +
                        "У подножия столбов можно найти множество пещер и гротов, где в древнем известняке сохранились отпечатки кораллов и окаменелости морских существ, напоминающие о том, что миллионы лет назад здесь было море . Одним из самых известных гротов является \"Столбовой\", который когда-то служил стоянкой для первобытных людей .\n" +
                        "\n" +
                        "Усьвинские столбы также известны как место съемок нескольких фильмов, включая \"Географ глобус пропил\" и \"Время первых\", что добавляет им дополнительной популярности и привлекательности .\n" +
                        "\n" +
                        "До Усьвинских столбов можно добраться на автомобиле или автобусе из Перми или Екатеринбурга. Путь к столбам проходит через живописные леса и поля, а подъем на вершину доступен даже для неподготовленных туристов, хотя часть маршрута может быть довольно крутой .\n" +
                        "\n" +
                        "Усьвинские столбы — это уникальное место, которое поражает своей красотой и величием, оставляя незабываемые впечатления у всех, кто его посетил ."
            ),
        )

        repositoryPlaces.insert(
            CPlace(
                UUID.randomUUID(),
                "Кунгурская ледяная пещера",
                saveMipmapToFile(context, R.mipmap.kungur_cave, "kungur_cave")?:"",
                "Кунгурская ледяная пещера — это уникальное природное чудо, расположенное в Пермском крае. Она является одной из крупнейших карстовых пещер в Европейской части России и седьмой по протяженности гипсовой пещерой в мире, с общей длиной около 5,7 километров 910. Пещера образовалась около 12 тысяч лет назад и представляет собой сложную систему подземных гротов и галерей, насчитывающую 48 гротов 11.\n" +
                        "\n" +
                        "Кунгурская пещера славится своими ледяными образованиями, которые создают атмосферу волшебства и таинственности. Внутри пещеры можно увидеть гигантские ледяные столбы, сталактиты и сталагмиты, а также множество подземных озер с кристально чистой водой 1213. Ледяные кристаллы украшают стены и потолки гротов, создавая захватывающее зрелище, особенно в зимний период 12.\n" +
                        "\n" +
                        "Температура в пещере круглый год остается низкой, около -2 до +3 градусов Цельсия, что способствует сохранению ледяных образований. Зимой в пещере наблюдается резкое различие температуры воздуха у пола и потолка, что приводит к образованию ледяных столбиков на полу 1415.\n" +
                        "\n" +
                        "Для туристов доступна часть пещеры протяженностью 1,7 километра, которая оборудована специальной подсветкой, делающей посещение еще более впечатляющим. Экскурсии по пещере проводятся круглый год, и каждый посетитель может насладиться великолепием подземного царства 1315.\n" +
                        "\n" +
                        "Кунгурская ледяная пещера — это не только природная достопримечательность, но и важный исторический объект. Она известна с давних времен и привлекает исследователей и туристов со всего мира. Посещение пещеры оставляет незабываемые впечатления и дарит уникальную возможность прикоснуться к тайнам природы 1211."
            )
        )
    }

    private fun saveMipmapToFile(context: Context, mipmapId: Int, fileName: String): String? {
        val imagesDir = File(context.filesDir, "images") // Папка /data/data/<package_name>/files/images
        if (!imagesDir.exists()) imagesDir.mkdirs() // Создаём папку, если её нет

        val imageFile = File(imagesDir, fileName)

        return try {
            val options = BitmapFactory.Options().apply {
                inSampleSize = 4 // Уменьшает изображение в 4 раза
            }
            val bitmap = BitmapFactory.decodeResource(context.resources, mipmapId, options)

            FileOutputStream(imageFile).use { fos ->
                bitmap.compress(Bitmap.CompressFormat.WEBP, 80, fos) // JPEG с 80% качеством
            }
            imageFile.absolutePath
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }


}