package ru.mobile.permtravel.pages.pageauthors

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
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
import ru.mobile.permtravel.R
import ru.mobile.permtravel.model.Author
import ru.mobile.permtravel.repositories.CRepositoryAuthors
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.UUID

class CViewModelPageAuthors(application: Application) : AndroidViewModel(application) {
    private val repositoryAuthors = CRepositoryAuthors(application)
    val authors: StateFlow<List<Author>> = repositoryAuthors.getAll()
        .stateIn(viewModelScope, SharingStarted.Lazily, listOf(
            Author(UUID.randomUUID(), "Загрузка...", "", "Ожидаем данные", "")
        ))
    init {
        val context = getApplication<Application>().applicationContext
        viewModelScope.launch(Dispatchers.IO) {
            if (repositoryAuthors.getAll().first().isEmpty()) {
                insertTestData(context)
           }
        }
    }
    private fun insertTestData(context: Context) {
        repositoryAuthors.insert(
            Author(
                UUID.randomUUID(),
                "Артемий Скалов",
                "instagram.com/artskalov_59",
                "Люблю искать скрытые жемчужины Прикамья: заброшенные деревни, старые церкви и нетуристические тропы. Если хочется увидеть Пермь по-новому — вам ко мне!",
                generateAvatar(context, "Артемий Скалов")?:""
            ),
        )
        repositoryAuthors.insert(
            Author(
                UUID.randomUUID(),
                "Виктория Лавина",
                "instagram.com/vlavina_perm",
                "Покажу Пермский край таким, каким вы его еще не видели! Водопады, скалы, ледяные пещеры и закаты над Камой. Готовы к приключениям? Тогда в путь",
                generateAvatar(context, "Виктория Лавина")?:""
            ),
        )
        repositoryAuthors.insert(
            Author(
                UUID.randomUUID(),
                "Елисей Громов",
                "instagram.com/gromov.travel",
                "Охочусь за бурями, громом и лучшими рассветами Урала! Фотографирую Пермь и окрестности с самых диких и высоких точек. Присоединяйтесь, если любите экстрим!",
                generateAvatar(context, "Елисей Громов")?:""
            ),
        )
        repositoryAuthors.insert(
            Author(
                UUID.randomUUID(),
                "Ксения Орлова",
                "instagram.com/ks_orlova59",
                "Люблю неспешные прогулки по старой Перми, деревянные особняки и уютные лесные тропы. Если вам близка атмосфера уюта и ностальгии — добро пожаловать! ",
                generateAvatar(context, "Ксения Орлова")?:""
            ),
        )
        repositoryAuthors.insert(
            Author(
                UUID.randomUUID(),
                "Даниил Северский",
                "instagram.com/danseversky",
                "Север Пермского края — моя стихия! Озёра, дремучие леса и глухие поселки, куда не доезжают туристы. Покажу настоящую глубинку и дикие места.",
                generateAvatar(context, "Даниил Северский")?:""
            ),
        )
        repositoryAuthors.insert(
            Author(
                UUID.randomUUID(),
                "Алина Куприянова",
                "instagram.com/alina.perm",
                "Живу в Перми, но каждый уикенд убегаю за город: горы, пещеры, сплавы, заброшки. Делюсь лучшими маршрутами и секретными локациями для идеального путешествия!",
                generateAvatar(context, "Алина Куприянова")?:""
            ),
        )
        repositoryAuthors.insert(
            Author(
                UUID.randomUUID(),
                "Олег Вереск",
                "instagram.com/veresk_trip",
                "Люблю природу, рыбалку, походы и хорошие байки у костра. В моем блоге — честные истории о жизни в Перми, местах силы и таинственных уголках края. ",
                generateAvatar(context, "Олег Вереск")?:""
            ),
        )
        repositoryAuthors.insert(
            Author(
                UUID.randomUUID(),
                "Софья Ветрова",
                "instagram.com/sof_wind_perm",
                "Путешествую по Уралу налегке, без плана и маршрута. Покажу, как можно открыть новые места, просто доверяя ветру и интуиции!",
                generateAvatar(context, "Софья Ветрова")?:""
            ),
        )
        repositoryAuthors.insert(
            Author(
                UUID.randomUUID(),
                "Руслан Камский",
                "instagram.com/kama_explorer",
                "Кама — моя главная дорога! Рассказываю про сплавы, рыбалку и самые красивые виды на пермские берега. Если любите воду и приключения — заходите!",
                generateAvatar(context, "Руслан Камский")?:""
            ),
        )
        repositoryAuthors.insert(
            Author(
                UUID.randomUUID(),
                "Полина Мостовая",
                "instagram.com/pol_most_travel",
                "С детства обожаю мосты, реки и панорамные виды. В моем блоге — лучшие точки для закатов, старинные постройки и необычные места Перми.",
                generateAvatar(context, "Полина Мостовая")?:""
            ),
        )
        repositoryAuthors.insert(
            Author(
                UUID.randomUUID(),
                "Тимур Лесницкий",
                "instagram.com/tim_lforest",
                "Лес — мой второй дом! Покажу, где найти самые живописные тропы, диких зверей и тишину, которой так не хватает в городе. Готовы к походу?",
                generateAvatar(context, "Тимур Лесницкий")?:""
            ),
        )
        repositoryAuthors.insert(
            Author(
                UUID.randomUUID(),
                "Наталья Зорина",
                "instagram.com/nat_zorin59",
                "Люблю исследовать мистические места и легенды Перми. Ведьмин камень, Чердынь, загадочные урочища — узнаете",
                generateAvatar(context, "Наталья Зорина")?:""
            ),
        )
    }

    private fun generateAvatar(context: Context, name: String): String {
        val firstLetter = name.firstOrNull()?.uppercase() ?: "?"
        val backgroundColor = getColorForLetter(firstLetter)

        val size = 200 // Размер изображения (px)
        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        val paint = Paint().apply {
            color = backgroundColor.toArgb()
            isAntiAlias = true
            style = Paint.Style.FILL
        }

        // Рисуем круглый фон
        canvas.drawCircle(size / 2f, size / 2f, size / 2f, paint)

        // Настройки текста
        val textPaint = Paint().apply {
            color = Color.White.toArgb()
            textSize = size / 2.5f
            textAlign = Paint.Align.CENTER
            typeface = Typeface.DEFAULT_BOLD
        }

        // Рисуем букву в центре
        val textBounds = Rect()
        textPaint.getTextBounds(firstLetter, 0, firstLetter.length, textBounds)
        val x = size / 2f
        val y = size / 2f - textBounds.exactCenterY()
        canvas.drawText(firstLetter, x, y, textPaint)

        // Создаём папку files/images, если её нет
        val imagesDir = File(context.filesDir, "images")
        if (!imagesDir.exists()) imagesDir.mkdirs()

        // Создаём файл для аватарки
        val fileName = "avatar_${name.hashCode()}.png"
        val file = File(imagesDir, fileName)

        // Сохраняем изображение в файл
        FileOutputStream(file).use { outputStream ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        }

        return file.absolutePath
    }

    private fun getColorForLetter(letter: String): Color {
        val colors = listOf(
            Color(0xFFEF5350), Color(0xFFAB47BC), Color(0xFF5C6BC0),
            Color(0xFF42A5F5), Color(0xFF26A69A), Color(0xFF66BB6A),
            Color(0xFFFFCA28), Color(0xFFFF7043)
        )
        val index = letter[0].code % colors.size
        return colors[index]
    }

}