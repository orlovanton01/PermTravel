package ru.mobile.permtravel.pages.pageplaces

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.mobile.permtravel.R
import ru.mobile.permtravel.model.CPlace
import ru.mobile.permtravel.repositories.CRepositoryPlaces
import java.util.UUID

class CViewModelPagePlaces(application: Application) :  AndroidViewModel(application) {
//    private val _places = mutableStateListOf<CPlace>(
//        CPlace(
//            UUID.randomUUID(),
//            "Усьвинские столбы",
//            R.drawable.usvinsky_pillars,
//            "Усьвинские столбы — это впечатляющий природный памятник, расположенный на берегу реки Усьва в Пермском крае. Эти величественные каменные образования тянутся вдоль берега на несколько километров и достигают высоты до 150 метров . Столбы представляют собой скальные выходы известняка, которые начали формироваться еще в Пермский период, около 200-300 миллионов лет назад .\n" +
//                    "\n" +
//                    "Усьвинские столбы являются популярным туристическим объектом, привлекающим тысячи посетителей каждый год. С вершины столбов открываются захватывающие виды на изгибы реки Усьва и окрестные ландшафты. На верхней части тропы расположены несколько смотровых площадок, откуда можно насладиться панорамными видами .\n" +
//                    "\n" +
//                    "У подножия столбов можно найти множество пещер и гротов, где в древнем известняке сохранились отпечатки кораллов и окаменелости морских существ, напоминающие о том, что миллионы лет назад здесь было море . Одним из самых известных гротов является \"Столбовой\", который когда-то служил стоянкой для первобытных людей .\n" +
//                    "\n" +
//                    "Усьвинские столбы также известны как место съемок нескольких фильмов, включая \"Географ глобус пропил\" и \"Время первых\", что добавляет им дополнительной популярности и привлекательности .\n" +
//                    "\n" +
//                    "До Усьвинских столбов можно добраться на автомобиле или автобусе из Перми или Екатеринбурга. Путь к столбам проходит через живописные леса и поля, а подъем на вершину доступен даже для неподготовленных туристов, хотя часть маршрута может быть довольно крутой .\n" +
//                    "\n" +
//                    "Усьвинские столбы — это уникальное место, которое поражает своей красотой и величием, оставляя незабываемые впечатления у всех, кто его посетил ."
//        ),
//        CPlace(
//            UUID.randomUUID(),
//            "Кунгурская ледяная пещера",
//            R.drawable.kungur_cave,
//            "Кунгурская ледяная пещера — это уникальное природное чудо, расположенное в Пермском крае. Она является одной из крупнейших карстовых пещер в Европейской части России и седьмой по протяженности гипсовой пещерой в мире, с общей длиной около 5,7 километров 910. Пещера образовалась около 12 тысяч лет назад и представляет собой сложную систему подземных гротов и галерей, насчитывающую 48 гротов 11.\n" +
//                    "\n" +
//                    "Кунгурская пещера славится своими ледяными образованиями, которые создают атмосферу волшебства и таинственности. Внутри пещеры можно увидеть гигантские ледяные столбы, сталактиты и сталагмиты, а также множество подземных озер с кристально чистой водой 1213. Ледяные кристаллы украшают стены и потолки гротов, создавая захватывающее зрелище, особенно в зимний период 12.\n" +
//                    "\n" +
//                    "Температура в пещере круглый год остается низкой, около -2 до +3 градусов Цельсия, что способствует сохранению ледяных образований. Зимой в пещере наблюдается резкое различие температуры воздуха у пола и потолка, что приводит к образованию ледяных столбиков на полу 1415.\n" +
//                    "\n" +
//                    "Для туристов доступна часть пещеры протяженностью 1,7 километра, которая оборудована специальной подсветкой, делающей посещение еще более впечатляющим. Экскурсии по пещере проводятся круглый год, и каждый посетитель может насладиться великолепием подземного царства 1315.\n" +
//                    "\n" +
//                    "Кунгурская ледяная пещера — это не только природная достопримечательность, но и важный исторический объект. Она известна с давних времен и привлекает исследователей и туристов со всего мира. Посещение пещеры оставляет незабываемые впечатления и дарит уникальную возможность прикоснуться к тайнам природы 1211."
//        )
//    )
    //Это кусок кода для вставки тестовых элементов в БД.
//    init {
//        kotlinx.coroutines.MainScope().launch(Dispatchers.IO) {
//
//            repositoryPlaces.insert(
//                CPlace(
//                    UUID.randomUUID(),
//                    "Усьвинские столбы",
//                    R.drawable.usvinsky_pillars,
//                    "Усьвинские столбы — это впечатляющий природный памятник, расположенный на берегу реки Усьва в Пермском крае. Эти величественные каменные образования тянутся вдоль берега на несколько километров и достигают высоты до 150 метров . Столбы представляют собой скальные выходы известняка, которые начали формироваться еще в Пермский период, около 200-300 миллионов лет назад .\n" +
//                            "\n" +
//                            "Усьвинские столбы являются популярным туристическим объектом, привлекающим тысячи посетителей каждый год. С вершины столбов открываются захватывающие виды на изгибы реки Усьва и окрестные ландшафты. На верхней части тропы расположены несколько смотровых площадок, откуда можно насладиться панорамными видами .\n" +
//                            "\n" +
//                            "У подножия столбов можно найти множество пещер и гротов, где в древнем известняке сохранились отпечатки кораллов и окаменелости морских существ, напоминающие о том, что миллионы лет назад здесь было море . Одним из самых известных гротов является \"Столбовой\", который когда-то служил стоянкой для первобытных людей .\n" +
//                            "\n" +
//                            "Усьвинские столбы также известны как место съемок нескольких фильмов, включая \"Географ глобус пропил\" и \"Время первых\", что добавляет им дополнительной популярности и привлекательности .\n" +
//                            "\n" +
//                            "До Усьвинских столбов можно добраться на автомобиле или автобусе из Перми или Екатеринбурга. Путь к столбам проходит через живописные леса и поля, а подъем на вершину доступен даже для неподготовленных туристов, хотя часть маршрута может быть довольно крутой .\n" +
//                            "\n" +
//                            "Усьвинские столбы — это уникальное место, которое поражает своей красотой и величием, оставляя незабываемые впечатления у всех, кто его посетил ."
//                ),
//            )
//
//            repositoryPlaces.insert(
//                CPlace(
//                    UUID.randomUUID(),
//                    "Кунгурская ледяная пещера",
//                    R.drawable.kungur_cave,
//                    "Кунгурская ледяная пещера — это уникальное природное чудо, расположенное в Пермском крае. Она является одной из крупнейших карстовых пещер в Европейской части России и седьмой по протяженности гипсовой пещерой в мире, с общей длиной около 5,7 километров 910. Пещера образовалась около 12 тысяч лет назад и представляет собой сложную систему подземных гротов и галерей, насчитывающую 48 гротов 11.\n" +
//                            "\n" +
//                            "Кунгурская пещера славится своими ледяными образованиями, которые создают атмосферу волшебства и таинственности. Внутри пещеры можно увидеть гигантские ледяные столбы, сталактиты и сталагмиты, а также множество подземных озер с кристально чистой водой 1213. Ледяные кристаллы украшают стены и потолки гротов, создавая захватывающее зрелище, особенно в зимний период 12.\n" +
//                            "\n" +
//                            "Температура в пещере круглый год остается низкой, около -2 до +3 градусов Цельсия, что способствует сохранению ледяных образований. Зимой в пещере наблюдается резкое различие температуры воздуха у пола и потолка, что приводит к образованию ледяных столбиков на полу 1415.\n" +
//                            "\n" +
//                            "Для туристов доступна часть пещеры протяженностью 1,7 километра, которая оборудована специальной подсветкой, делающей посещение еще более впечатляющим. Экскурсии по пещере проводятся круглый год, и каждый посетитель может насладиться великолепием подземного царства 1315.\n" +
//                            "\n" +
//                            "Кунгурская ледяная пещера — это не только природная достопримечательность, но и важный исторический объект. Она известна с давних времен и привлекает исследователей и туристов со всего мира. Посещение пещеры оставляет незабываемые впечатления и дарит уникальную возможность прикоснуться к тайнам природы 1211."
//                )
//            )
//        }
//    }

    private val repositoryPlaces = CRepositoryPlaces(application)

    val places: StateFlow<List<CPlace>> = repositoryPlaces.getAll()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addPlace(
        place : CPlace
    )
    {
        kotlinx.coroutines.MainScope().launch(Dispatchers.IO) {
            repositoryPlaces.insert(place)
        }
    }
}