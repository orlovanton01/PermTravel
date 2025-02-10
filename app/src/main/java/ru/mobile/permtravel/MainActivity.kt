package ru.mobile.permtravel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.yandex.mapkit.Animation
import ru.mobile.permtravel.ui.theme.PermTravelTheme
import ru.mobile.permtravel.pages.CPageLayout

import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView


class MainActivity : ComponentActivity() {

    private lateinit var mapview: MapView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        enableEdgeToEdge()
        setContent {
            PermTravelTheme {
                CPageLayout()
            }
        }

        MapKitFactory.setApiKey(BuildConfig.KEY)
        MapKitFactory.initialize(this)
        setContentView(R.layout.activity_main)

        mapview = findViewById(R.id.yandex_map)
        mapview.mapWindow.map.move(
            CameraPosition(
                Point(58.008252, 56.187958),
                17.0f,
                0.0f,
                0.0f
            ),
            Animation(Animation.Type.SMOOTH, 5f),
            null
        )
    }

    override fun onStop() {
        MapKitFactory.getInstance().onStop()
        mapview.onStop()

        super.onStop()
    }

    override fun onStart() {
        MapKitFactory.getInstance().onStart()
        mapview.onStart()

        super.onStart()
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    PermTravelTheme {
        CPageLayout()
    }
}